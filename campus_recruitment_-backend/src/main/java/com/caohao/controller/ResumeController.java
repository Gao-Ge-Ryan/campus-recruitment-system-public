package com.caohao.controller;

import com.caohao.common.Result;
import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.UserResumeDao;
import com.caohao.pojo.entity.FileInfo;
import com.caohao.pojo.entity.UserResume;
import com.caohao.security.util.GetTokenInfoUtil;
import com.google.gson.Gson;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RestController
@RequestMapping("/resume")
@CrossOrigin
public class ResumeController {
    private static final String REQUEST_PATH = "http://82.157.42.25:5050/file/upload";
    private static final String BOUNDARY = "20220501";

    @Resource
    private UserResumeDao userResumeDao;

    @PostMapping("/upload")
    public Result resumeUpload(@RequestParam("file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String tail = originalFilename.substring(originalFilename.lastIndexOf("."));

        UserResume userResume = new UserResume();
        userResume.setUserId(GetTokenInfoUtil.getUsername());
        userResume.setCreateTime(DateUtil.getCurrentTimeMillis());
        userResume.setResumeName(originalFilename);
        if (tail.equals(".pdf")) {
            String localFileDirPath = System.getProperty("user.dir") + File.separator + "fileDir";
            File localFileDir = new File(localFileDirPath);
            if (!localFileDir.exists()) {
                localFileDir.mkdirs();
            }
            String localFilePath = localFileDirPath + File.separator + System.currentTimeMillis() + file.getOriginalFilename();
            File localFile = new File(localFilePath);
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            file.transferTo(localFile);
            Result<Map<String, Object>> fileInfoResult = uploadFileHttpPost(localFilePath);
            System.out.println("成功" + fileInfoResult.getData().get("accessAddress"));
            userResume.setId(IDGenerator.StringID());
            userResume.setExtension(".pdf");
            userResume.setType(".pdf");

            userResume.setResumeType(0);
            userResume.setResumeUrl(fileInfoResult.getData().get("accessAddress").toString());
            userResumeDao.insert(userResume);
        }
        if (tail.equals(".doc")) {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String PathStr = path.getAbsolutePath() + "\\static";
            File localFileDir1 = new File(PathStr);
            if (!localFileDir1.exists()) {
                localFileDir1.mkdirs();
            }
            String fileNameTrance=System.currentTimeMillis() + file.getOriginalFilename();
            String localFilePath1 = PathStr + File.separator + fileNameTrance;
            System.out.println(localFilePath1);
            File localFile1 = new File(localFilePath1);
            if (!localFile1.exists()) {
                localFile1.createNewFile();
            }
            file.transferTo(localFile1);
            Result<Map<String, Object>> fileInfoResult = uploadFileHttpPost(localFilePath1);
            System.out.println("成功" + fileInfoResult.getData().get("accessAddress"));
            String wordDocToHtml = wordDocToHtml(fileNameTrance);
            userResume.setId(IDGenerator.StringID());
            userResume.setExtension(".doc");
            userResume.setType(fileInfoResult.getData().get("accessAddress").toString());

            userResume.setResumeType(1);
            userResume.setResumeUrl(wordDocToHtml);
            userResumeDao.insert(userResume);

        }
        if (tail.equals(".docx")) {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            String PathStr = path.getAbsolutePath() + "\\static";
            File localFileDir1 = new File(PathStr);
            if (!localFileDir1.exists()) {
                localFileDir1.mkdirs();
            }
            String fileNameTrance=System.currentTimeMillis() + file.getOriginalFilename();
            String localFilePath1 = PathStr + File.separator + fileNameTrance;
            System.out.println(localFilePath1);
            File localFile1 = new File(localFilePath1);
            if (!localFile1.exists()) {
                localFile1.createNewFile();
            }
            file.transferTo(localFile1);
            Result<Map<String, Object>> fileInfoResult = uploadFileHttpPost(localFilePath1);
            System.out.println("成功" + fileInfoResult.getData().get("accessAddress"));
            String wordDocToHtml = wordDocxToHtml(fileNameTrance);
            userResume.setId(IDGenerator.StringID());
            userResume.setExtension(".docx");
            userResume.setType(fileInfoResult.getData().get("accessAddress").toString());

            userResume.setResumeType(2);
            userResume.setResumeUrl(wordDocToHtml);
            userResumeDao.insert(userResume);

        }
        return Result.ok(userResume);
    }

    /**
     * 方法封装 文件上传 http post请求
     */
    public static Result<Map<String, Object>> uploadFileHttpPost(String filePathName) throws IOException {
        // 设置连接
        URL url = new URL(REQUEST_PATH);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        httpConn.setUseCaches(false);
        httpConn.setConnectTimeout(3000);
        httpConn.setReadTimeout(3000);
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        OutputStream os = httpConn.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);

        String content = "--" + BOUNDARY + "\r\n";
        content += "Content-Disposition: form-data; name=\"file\"" + "\r\n\r\n";
        content += "我是post数据的值";
        content += "\r\n--" + BOUNDARY + "\r\n";
        content += "Content-Disposition: form-data; name=\"file\"; filename=\"" + filePathName + "\"" + "\r\n";
        content += "Content-Type: application/octet-stream\r\n\r\n";
        bos.write(content.getBytes());

        // 开始写出文件的二进制数据
        FileInputStream fin = new FileInputStream(new File(filePathName));
        BufferedInputStream bfi = new BufferedInputStream(fin);
        byte[] buffer = new byte[4096];
        int bytes = bfi.read(buffer, 0, buffer.length);
        while (bytes != -1) {
            bos.write(buffer, 0, bytes);
            bytes = bfi.read(buffer, 0, buffer.length);
        }
        bfi.close();
        fin.close();
        bos.write(("\r\n--" + BOUNDARY).getBytes());
        bos.flush();
        bos.close();
        os.close();

        // 读取返回数据
        StringBuffer strBuf = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpConn.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        String res = strBuf.toString();
        System.out.println(res);
        reader.close();
        httpConn.disconnect();
        Result<FileInfo> fileInfoResult = new Result<>();
        Result result = new Gson().fromJson(res, fileInfoResult.getClass());
        return result;
    }

    /**
     * word2HTML   doc方法
     */
    public static String wordDocToHtml(String fileName) throws Exception, FileNotFoundException, ParserConfigurationException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String imagePathStr = path.getAbsolutePath() + "\\static\\image\\";
        String sourceFileName = path.getAbsolutePath() + "\\static\\"+fileName;
        String newAddress = System.currentTimeMillis()+".html";
        String targetFileName = path.getAbsolutePath() + "\\static\\"+newAddress;
        File file = new File(imagePathStr);
        if (!file.exists()) {
            file.mkdirs();
        }
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        //保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        return newAddress;
    }

    /**
     * word2HTML   docx方法
     */
    public static String wordDocxToHtml(String fileName) throws Exception {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String imagePath = path.getAbsolutePath() + "\\static\\image";
        String sourceFileName = path.getAbsolutePath() + "\\static\\"+fileName;
        String newAddress = System.currentTimeMillis()+".html";
        String targetFileName = path.getAbsolutePath() + "\\static\\"+newAddress;

        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }
        return newAddress;
    }
}

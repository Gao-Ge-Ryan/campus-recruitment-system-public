package com.caohao.security.config;

/*
没有解决跨域这个方法，因为茈跨域过滤器在security之后
 */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    /**
//     * 开启跨域
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 设置允许跨域的路由
//        registry.addMapping("/**")
//                // 设置允许跨域请求的域名
//                .allowedOriginPatterns("*")
//                // 是否允许证书（cookies）
//                .allowCredentials(true)
//                // 设置允许的方法
//                .allowedMethods("*")
//                // 跨域允许时间
//                .maxAge(3600);
//    }
//
//}

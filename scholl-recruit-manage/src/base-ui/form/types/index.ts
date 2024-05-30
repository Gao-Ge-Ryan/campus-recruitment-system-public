interface ISelectOption {
  label: string
  value: any
}

type ItemType = 'input' | 'password' | 'select' | 'datepicker'

export interface IFormItem {
  field: string
  type: ItemType
  label: string
  placeHolder?: string
  rules?: any[]
  options?: ISelectOption[]
  otherOption?: any
  defaultValue?: any
  isHidden?: boolean
}

export interface IForm {
  title?: string
  formItems?: IFormItem[]
  labelWidth?: string
  itemStyle: any
  colLayout: any
}

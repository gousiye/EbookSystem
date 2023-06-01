import request from '@/utils/request'
import qs from 'qs'
export function Query(params) {
  return request({
    url: 'http://localhost/table/ebook',
    method: 'get',
    params: params,
    paramsSerializer: params => {
      return qs.stringify(params, { indices: false })
    }
  })
}

// 获得数据库中存储的所有类别
export function GetClass(params) {
  return request({
    url: 'http://localhost/table/init/class',
    method: 'get',
    params: params,

  })
}

// 获得数据库中存储的所有文件格式
export function GetSuffix(params) {
  return request({
    url: 'http://localhost/table/init/suffix',
    method: 'get',
    params: params
  })
}


// 上传书籍
export function Add(ebook) {
  return request({
    url: 'http://localhost/table/add',
    headers: { 'Content-Type': 'application/json' },
    method: 'post',
    data: JSON.stringify({
      bookName: ebook.bookName,
      authorName: ebook.authorName,
      classifications: ebook.classifications,
      suffix: ebook.suffix,
      language: ebook.language,
      download: ebook.download,
      introduction: ebook.introduction,
      publisher: ebook.publisher
    }),
  })
}

// 上传书籍文件
export function Upload(params) {
  return request({
    url: 'http://localhost/table/upload',
    method: 'post',
    data: params
  })
}


//获得一本书的详细信息
export function GetABookInfo(params) {
  return request({
    url: 'http://localhost/table/info',
    method: 'get',
    params: params
  })
}

// 删除一本书
export function DeleteABook(params) {
  return request({
    url: 'http://localhost/table/delete',
    method: "get",
    params: params
  })
}

// 批量删除书籍
export function MultiDelete(params) {
  return request({
    url: 'http://localhost/table/multidelete',
    method: "post",
    data: params
  })
}

//获取一本书的URL
export function GetABookURL(params) {
  return request({
    url: 'http://localhost/table/bookUrl',
    method: "get",
    params: params
  })
}


//更新下载量
export function UpdateDownload(params) {
  return request({
    url: 'http://localhost/table/UpdateDownload',
    method: "get",
    params: params,
  })
}
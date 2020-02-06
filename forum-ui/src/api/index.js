/* eslint-disable no-console */
import axios from 'axios'
import qs from 'qs'

let instance = axios.create({
    baseURL: 'http://106.12.61.131:8081',
    headers: {
        "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
    },
    withCredentials: true // 跨域
})

function get(url, params) {
    return new Promise((resolve, reject) => {
        instance.request({
            url: url,
            method: 'get',
            params,
        }).then(({ data }) => {
            console.log(data.data)
            if (data.resultCode !== 0) {
                console.log('data.resultCode !== 0  -----'+data.data)
                return reject(data)
            }
            console.log('data.resultCode == 0  -----'+data.data)
            return resolve(data)
        }).catch(({ response }) => {
            return reject({
                resultCode: response.status,
                resultInfo: response.statusText
            })
        })
    })
}

function post(url, data) {
    return new Promise((resolve, reject) => {
        instance.request({
            url: url,
            method: 'post',
            data: qs.stringify(data) // application/x-www-form-urlencoded
        }).then(({ data }) => {
            if (!data.data/* data.resultCode !== 0 */) {
                console.log(data.data)
                return reject(data)
            }
            return resolve(data)
        }).catch(({ response }) => {
            return reject({
                resultCode: response.status,
                resultInfo: response.statusText
            })
        })
    })
}

export default {
    register(data) {
        return post('/user/regist', data)
    },
    login(data) {
        return post('/user/login', data)
    },
    checkLogin(data) {
        return post('/user/login', data)
    },
    logout(data) {
        return instance.get('/user/exit', {params: {uid: data.uid}})
    },
    getArticleList(data) {
        return get('/article/list', data)
    },
    getUserInfo(data) {
        return get('/user/info', data)
    }
}

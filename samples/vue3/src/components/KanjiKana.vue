<!--
 Copyright (c) 2024 デジタル庁
 
 This software is released under the MIT License.
 https://opensource.org/licenses/MIT
-->

<template>
    <div class="flex justify-center">
        <div class="flex flex-col justify-start items-center gap-2.5 px-2.5 py-2 w-full">
            漢字カナ突合アプリ
        </div>
    </div>
    <div class="flex flex-col justify-start items-center gap-2.5 px-2.5 py-2">
        <div class="flex flex-col justify-start items-center gap-2.5 px-2.5 py-2 w-full">
            漢字とカナを入力し，突合ボタンを押してください
        </div>
    </div>
    <div class="flex flex-col justify-start items-center gap-2.5 px-2.5 py-2">
        <div class="grid gap-6 mb-6 md:grid-cols-2">

            <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">漢字姓名</label>
            <input type="text" size=50 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="kanji" required />

        </div>
        <div class="grid gap-6 mb-6 md:grid-cols-2">

            <label  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">カナ姓名</label>
            <input type="text" size=50 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="kana" required />

        </div>
        <div class="grid gap-6 mb-6 md:grid-cols-2">

            <label  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">API URL</label>
            <input type="text" size=50 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="apiurl" required />

        </div>
        <div class="grid gap-6 mb-6 md:grid-cols-2">

            <label  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">キー</label>
            <input type="text" size=50 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="apikey" required />

        </div>

        <div class="grid gap-6 mb-6 md:grid-cols-2">

            <label  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">詳細に調べる</label>
            <input type="checkbox" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="detail"  />

        </div>
        <div>
            <button type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800" @click="calc()">突合！</button>
        </div>

        <div class="grid gap-6 mb-6 md:grid-cols-2">

        <label  class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">結果</label>
        <div class="block mb-2 text-sm font-medium text-gray-900 dark:text-white" />{{  result  }}

        </div>

    </div>
    <teleport to="body">
        <div v-if="loading" class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 p-4 md:inset-0 h-modal md:h-full">
            <VueLoading v-model:active="loading" :opacity=0.4 class="h-screen w-screen flex justify-center items-center" />
        </div>
    </teleport>
</template>

<script setup lang="ts">
import axios from 'axios'
import { ref } from "vue";
import VueLoading from 'vue-loading-overlay'
import 'vue-loading-overlay/dist/css/index.css'
const kanji=ref('日本［東京］　花子')
const kana=ref('トウキョウ　ハナコ')
const apikey=ref("please_input_api_key")
const apiurl=ref("https://api.trueno-kktg.digital.go.jp/v1/")
const detail=ref(true)
const result=ref('')
const loading=ref(false)

const calc=()=>{
    result.value=""
    console.log("kanji="+kanji.value+",kana="+kana.value+",detail="+detail.value)
    let path='simple'
    if(detail.value){
        path='detail'
    }
    let url=apiurl.value
    if(!url.endsWith("/")){
        url+="/"
    }
    let query=url+path+"?kanji="+encodeURI(kanji.value)+"&kana="+encodeURI(kana.value)+"&key="+apikey.value
    console.log(query)

    loading.value=true
    axios.get(query).then(response =>{
        console.log(response)
        result.value=response.data
        loading.value=false
    })
    .catch(error=>{
        alert(error)
        loading.value=false
    })
}


</script>

<template>
  <div>
    <p>여기는 자식 컴포넌트</p>
    <p>부모로 부터 받은 데이터는 아래와 같다.</p>
    <p>{{sendProps1}}</p>
    <p>{{sendProps2}}</p>
    <p v-for="(value,key) in sendProps3" :key="key">{{key}} : {{value}}</p>
  </div>
</template>

<script setup>
import { watch } from "vue";

const {sendProps1, sendProps2, sendProps3} = defineProps({
  sendProps1: String,
  sendProps2: Number,
  sendProps3: Object
});


</script>

<style lang="scss" scoped></style>

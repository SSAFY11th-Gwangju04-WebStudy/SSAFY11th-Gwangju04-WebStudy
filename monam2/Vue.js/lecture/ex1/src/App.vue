<template>
  <div>
    <ChildComponent :sendProps1="title" :sendProps2="createAt" :sendProps3="obj"/>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import ChildComponent from "./components/ChildComponent.vue";

const title = ref("부모 컴포넌트의 데이터 입니다.");
const createAt = ref(2024);
const obj = reactive({
  id : 111,
  name : "john"
});
</script>
<style scoped></style>

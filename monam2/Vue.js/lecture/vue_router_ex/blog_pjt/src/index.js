import { createWebHistory, createRouter } from "vue-router";
import ExPage from "./components/ExPage.vue";
import List from "./components/List.vue";
import DetailView from "./components/DetailView.vue";



const routes = [
  {
    path: "/ex",
    component: ExPage,
  },
  {
    path: "/list",
    component: List,
  },
  {
    path: "/detail/:id",
    component: DetailView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router; 
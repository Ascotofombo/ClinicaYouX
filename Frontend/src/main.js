import Vue from 'vue'
import App from './App.vue'

import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'leaflet/dist/leaflet.css';
import {routes} from './routes';
import {Icon} from "leaflet/dist/leaflet-src.esm";
import VueTheMask from 'vue-the-mask'
import lang from 'element-ui/lib/locale/lang/pt-br';
import locale from 'element-ui/lib/locale';


delete Icon.Default.prototype._getIconUrl;

locale.use(lang);

Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(Element);
Vue.use(VueTheMask);

Icon.Default.mergeOptions({
  iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
  iconUrl: require("leaflet/dist/images/marker-icon.png"),
  shadowUrl: require("leaflet/dist/images/marker-shadow.png")
});

const router = new VueRouter({
  routes: routes
})

new Vue({
  el: '#app',
  router: router,
  render: h => h(App)
})

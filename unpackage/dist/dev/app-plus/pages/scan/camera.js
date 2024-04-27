"use weex:vue";
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/*!*********************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/main.js?{"page":"pages%2Fscan%2Fcamera"} ***!
  \*********************************************************************************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! uni-app-style */ 1);\n/* harmony import */ var uni_app_style__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(uni_app_style__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! uni-polyfill */ 4);\n/* harmony import */ var uni_polyfill__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(uni_polyfill__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var _pages_scan_camera_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./pages/scan/camera.nvue?mpType=page */ 5);\n\n        \n        \n        \n        \n        _pages_scan_camera_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].mpType = 'page'\n        _pages_scan_camera_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].route = 'pages/scan/camera'\n        _pages_scan_camera_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"].el = '#root'\n        new Vue(_pages_scan_camera_nvue_mpType_page__WEBPACK_IMPORTED_MODULE_2__[\"default\"])\n        //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7O0FBRUEsUUFBOEI7QUFDOUIsUUFBNkI7QUFDN0IsUUFBOEQ7QUFDOUQsUUFBUSwyRUFBRztBQUNYLFFBQVEsMkVBQUc7QUFDWCxRQUFRLDJFQUFHO0FBQ1gsZ0JBQWdCLDJFQUFHIiwiZmlsZSI6IjAuanMiLCJzb3VyY2VzQ29udGVudCI6WyJcbiAgICAgICAgXG4gICAgICAgIGltcG9ydCAndW5pLWFwcC1zdHlsZSdcbiAgICAgICAgaW1wb3J0ICd1bmktcG9seWZpbGwnXG4gICAgICAgIGltcG9ydCBBcHAgZnJvbSAnLi9wYWdlcy9zY2FuL2NhbWVyYS5udnVlP21wVHlwZT1wYWdlJ1xuICAgICAgICBBcHAubXBUeXBlID0gJ3BhZ2UnXG4gICAgICAgIEFwcC5yb3V0ZSA9ICdwYWdlcy9zY2FuL2NhbWVyYSdcbiAgICAgICAgQXBwLmVsID0gJyNyb290J1xuICAgICAgICBuZXcgVnVlKEFwcClcbiAgICAgICAgIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///0\n");

/***/ }),
/* 1 */
/*!********************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/main.js?{"type":"appStyle"} ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("Vue.prototype.__$appStyle__ = {}\nVue.prototype.__merge_style && Vue.prototype.__merge_style(__webpack_require__(/*! ./App.vue?vue&type=style&index=0&lang=css */ 2).default,Vue.prototype.__$appStyle__)\n//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQ0EsMkRBQTJELG1CQUFPLENBQUMsa0RBQTJDIiwiZmlsZSI6IjEuanMiLCJzb3VyY2VzQ29udGVudCI6WyJWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18gPSB7fVxuVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlICYmIFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZShyZXF1aXJlKFwiLi9BcHAudnVlP3Z1ZSZ0eXBlPXN0eWxlJmluZGV4PTAmbGFuZz1jc3NcIikuZGVmYXVsdCxWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18pXG4iXSwic291cmNlUm9vdCI6IiJ9\n//# sourceURL=webpack-internal:///1\n");

/***/ }),
/* 2 */
/*!********************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/App.vue?vue&type=style&index=0&lang=css ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!../../HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--10-oneOf-0-2!../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./App.vue?vue&type=style&index=0&lang=css */ 3);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_10_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_10_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_App_vue_vue_type_style_index_0_lang_css__WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),
/* 3 */
/*!*********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-1!./node_modules/postcss-loader/src??ref--10-oneOf-0-2!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--10-oneOf-0-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/GitHub_Project/music-frontend/App.vue?vue&type=style&index=0&lang=css ***!
  \*********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  "@VERSION": 2
}

/***/ }),
/* 4 */
/*!*******************************************************************!*\
  !*** ./node_modules/@dcloudio/uni-cli-shared/lib/uni-polyfill.js ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function (callback) {
    var promise = this.constructor;
    return this.then(function (value) {
      return promise.resolve(callback()).then(function () {
        return value;
      });
    }, function (reason) {
      return promise.resolve(callback()).then(function () {
        throw reason;
      });
    });
  };
}
if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  var global = uni.requireGlobal();
  ArrayBuffer = global.ArrayBuffer;
  Int8Array = global.Int8Array;
  Uint8Array = global.Uint8Array;
  Uint8ClampedArray = global.Uint8ClampedArray;
  Int16Array = global.Int16Array;
  Uint16Array = global.Uint16Array;
  Int32Array = global.Int32Array;
  Uint32Array = global.Uint32Array;
  Float32Array = global.Float32Array;
  Float64Array = global.Float64Array;
  BigInt64Array = global.BigInt64Array;
  BigUint64Array = global.BigUint64Array;
}

/***/ }),
/* 5 */
/*!***************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?mpType=page ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./camera.nvue?vue&type=template&id=9b2412c0&mpType=page */ 6);\n/* harmony import */ var _camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./camera.nvue?vue&type=script&lang=js&mpType=page */ 8);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js */ 12);\n\nvar renderjs\n\n\nfunction injectStyles (context) {\n  \n  if(!this.options.style){\n          this.options.style = {}\n      }\n      if(Vue.prototype.__merge_style && Vue.prototype.__$appStyle__){\n        Vue.prototype.__merge_style(Vue.prototype.__$appStyle__, this.options.style)\n      }\n      if(Vue.prototype.__merge_style){\n                Vue.prototype.__merge_style(__webpack_require__(/*! ./camera.nvue?vue&type=style&index=0&lang=scss&mpType=page */ 10).default, this.options.style)\n            }else{\n                Object.assign(this.options.style,__webpack_require__(/*! ./camera.nvue?vue&type=style&index=0&lang=scss&mpType=page */ 10).default)\n            }\n\n}\n\n/* normalize component */\n\nvar component = Object(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_runtime_componentNormalizer_js__WEBPACK_IMPORTED_MODULE_2__[\"default\"])(\n  _camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_1__[\"default\"],\n  _camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"render\"],\n  _camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"staticRenderFns\"],\n  false,\n  null,\n  null,\n  \"75188308\",\n  false,\n  _camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__[\"components\"],\n  renderjs\n)\n\ninjectStyles.call(component)\ncomponent.options.__file = \"pages/scan/camera.nvue\"\n/* harmony default export */ __webpack_exports__[\"default\"] = (component.exports);//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBK0g7QUFDL0g7QUFDc0U7QUFDTDtBQUNqRTs7QUFFQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLDRDQUE0QyxtQkFBTyxDQUFDLG9FQUE0RDtBQUNoSCxhQUFhO0FBQ2IsaURBQWlELG1CQUFPLENBQUMsb0VBQTREO0FBQ3JIOztBQUVBOztBQUVBO0FBQ21LO0FBQ25LLGdCQUFnQiw2S0FBVTtBQUMxQixFQUFFLHdGQUFNO0FBQ1IsRUFBRSw2RkFBTTtBQUNSLEVBQUUsc0dBQWU7QUFDakI7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBLEVBQUUsaUdBQVU7QUFDWjtBQUNBOztBQUVBO0FBQ0E7QUFDZSxnRiIsImZpbGUiOiI1LmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgcmVuZGVyLCBzdGF0aWNSZW5kZXJGbnMsIHJlY3ljbGFibGVSZW5kZXIsIGNvbXBvbmVudHMgfSBmcm9tIFwiLi9jYW1lcmEubnZ1ZT92dWUmdHlwZT10ZW1wbGF0ZSZpZD05YjI0MTJjMCZtcFR5cGU9cGFnZVwiXG52YXIgcmVuZGVyanNcbmltcG9ydCBzY3JpcHQgZnJvbSBcIi4vY2FtZXJhLm52dWU/dnVlJnR5cGU9c2NyaXB0Jmxhbmc9anMmbXBUeXBlPXBhZ2VcIlxuZXhwb3J0ICogZnJvbSBcIi4vY2FtZXJhLm52dWU/dnVlJnR5cGU9c2NyaXB0Jmxhbmc9anMmbXBUeXBlPXBhZ2VcIlxuZnVuY3Rpb24gaW5qZWN0U3R5bGVzIChjb250ZXh0KSB7XG4gIFxuICBpZighdGhpcy5vcHRpb25zLnN0eWxlKXtcbiAgICAgICAgICB0aGlzLm9wdGlvbnMuc3R5bGUgPSB7fVxuICAgICAgfVxuICAgICAgaWYoVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlICYmIFZ1ZS5wcm90b3R5cGUuX18kYXBwU3R5bGVfXyl7XG4gICAgICAgIFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZShWdWUucHJvdG90eXBlLl9fJGFwcFN0eWxlX18sIHRoaXMub3B0aW9ucy5zdHlsZSlcbiAgICAgIH1cbiAgICAgIGlmKFZ1ZS5wcm90b3R5cGUuX19tZXJnZV9zdHlsZSl7XG4gICAgICAgICAgICAgICAgVnVlLnByb3RvdHlwZS5fX21lcmdlX3N0eWxlKHJlcXVpcmUoXCIuL2NhbWVyYS5udnVlP3Z1ZSZ0eXBlPXN0eWxlJmluZGV4PTAmbGFuZz1zY3NzJm1wVHlwZT1wYWdlXCIpLmRlZmF1bHQsIHRoaXMub3B0aW9ucy5zdHlsZSlcbiAgICAgICAgICAgIH1lbHNle1xuICAgICAgICAgICAgICAgIE9iamVjdC5hc3NpZ24odGhpcy5vcHRpb25zLnN0eWxlLHJlcXVpcmUoXCIuL2NhbWVyYS5udnVlP3Z1ZSZ0eXBlPXN0eWxlJmluZGV4PTAmbGFuZz1zY3NzJm1wVHlwZT1wYWdlXCIpLmRlZmF1bHQpXG4gICAgICAgICAgICB9XG5cbn1cblxuLyogbm9ybWFsaXplIGNvbXBvbmVudCAqL1xuaW1wb3J0IG5vcm1hbGl6ZXIgZnJvbSBcIiEuLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL3J1bnRpbWUvY29tcG9uZW50Tm9ybWFsaXplci5qc1wiXG52YXIgY29tcG9uZW50ID0gbm9ybWFsaXplcihcbiAgc2NyaXB0LFxuICByZW5kZXIsXG4gIHN0YXRpY1JlbmRlckZucyxcbiAgZmFsc2UsXG4gIG51bGwsXG4gIG51bGwsXG4gIFwiNzUxODgzMDhcIixcbiAgZmFsc2UsXG4gIGNvbXBvbmVudHMsXG4gIHJlbmRlcmpzXG4pXG5cbmluamVjdFN0eWxlcy5jYWxsKGNvbXBvbmVudClcbmNvbXBvbmVudC5vcHRpb25zLl9fZmlsZSA9IFwicGFnZXMvc2Nhbi9jYW1lcmEubnZ1ZVwiXG5leHBvcnQgZGVmYXVsdCBjb21wb25lbnQuZXhwb3J0cyJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///5\n");

/***/ }),
/* 6 */
/*!*********************************************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=template&id=9b2412c0&mpType=page ***!
  \*********************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./camera.nvue?vue&type=template&id=9b2412c0&mpType=page */ 7);
/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "render", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__["render"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__["staticRenderFns"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__["recyclableRender"]; });

/* harmony reexport (safe) */ __webpack_require__.d(__webpack_exports__, "components", function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_loaders_templateLoader_js_vue_loader_options_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_template_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_uni_app_loader_page_meta_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_8_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_template_id_9b2412c0_mpType_page__WEBPACK_IMPORTED_MODULE_0__["components"]; });



/***/ }),
/* 7 */
/*!********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/loaders/templateLoader.js??vue-loader-options!./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/template.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-uni-app-loader/page-meta.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--8-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=template&id=9b2412c0&mpType=page ***!
  \********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! exports provided: render, staticRenderFns, recyclableRender, components */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "render", function() { return render; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "staticRenderFns", function() { return staticRenderFns; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "recyclableRender", function() { return recyclableRender; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "components", function() { return components; });
var components
var render = function () {
  var _vm = this
  var _h = _vm.$createElement
  var _c = _vm._self._c || _h
  return _c(
    "scroll-view",
    {
      staticStyle: { flexDirection: "column" },
      attrs: {
        scrollY: true,
        showScrollbar: true,
        enableBackToTop: true,
        bubble: "true",
      },
    },
    [
      _c(
        "view",
        {
          staticClass: ["music-camera"],
          style: { width: _vm.windowWidth, height: _vm.windowHeight },
        },
        [
          _c(
            "view",
            {
              staticClass: ["preview"],
              style: { width: _vm.windowWidth, height: _vm.windowHeight - 80 },
            },
            [
              _c("live-pusher", {
                ref: "livePusher",
                staticClass: ["livePusher"],
                style: { width: _vm.cameraWidth, height: _vm.cameraHeight },
                attrs: {
                  id: "livePusher",
                  mode: "FHD",
                  beauty: "1",
                  whiteness: "0",
                  aspect: _vm.aspect,
                  minBitrate: "1000",
                  audioQuality: "16KHz",
                  devicePosition: "front",
                  autoFocus: true,
                  muted: true,
                  enableCamera: true,
                  enableMic: false,
                  zoom: false,
                },
                on: { statechange: _vm.statechange },
              }),
              _c("cover-view", { staticClass: ["remind"] }, [
                _c(
                  "u-text",
                  {
                    staticClass: ["remind-text"],
                    appendAsTree: true,
                    attrs: { append: "tree" },
                  },
                  [_vm._v(_vm._s(_vm.message))]
                ),
              ]),
              _c(
                "cover-view",
                {
                  staticClass: ["outline-box"],
                  style: {
                    width: _vm.windowWidth,
                    height: _vm.windowHeight - 255,
                  },
                },
                [
                  _c("cover-image", {
                    staticClass: ["outline-img"],
                    attrs: { src: "/static/outline/idphotoskin.png" },
                  }),
                ],
                1
              ),
            ],
            1
          ),
          _c(
            "view",
            { staticClass: ["menu"] },
            [
              _c("cover-image", {
                staticClass: ["menu-mask"],
                attrs: { src: "/static/bar.png" },
              }),
              _c("cover-image", {
                staticClass: ["menu-back"],
                attrs: { src: "/static/back.png" },
                on: { click: _vm.back },
              }),
              _c("cover-image", {
                staticClass: ["menu-snapshot"],
                attrs: { src: "/static/shutter.png" },
                on: { click: _vm.snapshot },
              }),
              _c("cover-image", {
                staticClass: ["menu-flip"],
                attrs: { src: "/static/flip.png" },
                on: { click: _vm.flip },
              }),
            ],
            1
          ),
        ]
      ),
    ]
  )
}
var recyclableRender = false
var staticRenderFns = []
render._withStripped = true



/***/ }),
/* 8 */
/*!***************************************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=script&lang=js&mpType=page ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../HBuilderX/plugins/uniapp-cli/node_modules/babel-loader/lib??ref--5-0!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./camera.nvue?vue&type=script&lang=js&mpType=page */ 9);\n/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if([\"default\"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));\n /* harmony default export */ __webpack_exports__[\"default\"] = (_HBuilderX_plugins_uniapp_cli_node_modules_babel_loader_lib_index_js_ref_5_0_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_5_1_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_script_lang_js_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); //# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbbnVsbF0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQWliLENBQWdCLHNkQUFHLEVBQUMiLCJmaWxlIjoiOC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBtb2QgZnJvbSBcIi0hLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvYmFiZWwtbG9hZGVyL2xpYi9pbmRleC5qcz8/cmVmLS01LTAhLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvQGRjbG91ZGlvL3Z1ZS1jbGktcGx1Z2luLXVuaS9wYWNrYWdlcy93ZWJwYWNrLXByZXByb2Nlc3MtbG9hZGVyL2luZGV4LmpzPz9yZWYtLTUtMSEuLi8uLi8uLi8uLi9IQnVpbGRlclgvcGx1Z2lucy91bmlhcHAtY2xpL25vZGVfbW9kdWxlcy9AZGNsb3VkaW8vdnVlLWNsaS1wbHVnaW4tdW5pL3BhY2thZ2VzL3Z1ZS1sb2FkZXIvbGliL2luZGV4LmpzPz92dWUtbG9hZGVyLW9wdGlvbnMhLi9jYW1lcmEubnZ1ZT92dWUmdHlwZT1zY3JpcHQmbGFuZz1qcyZtcFR5cGU9cGFnZVwiOyBleHBvcnQgZGVmYXVsdCBtb2Q7IGV4cG9ydCAqIGZyb20gXCItIS4uLy4uLy4uLy4uL0hCdWlsZGVyWC9wbHVnaW5zL3VuaWFwcC1jbGkvbm9kZV9tb2R1bGVzL2JhYmVsLWxvYWRlci9saWIvaW5kZXguanM/P3JlZi0tNS0wIS4uLy4uLy4uLy4uL0hCdWlsZGVyWC9wbHVnaW5zL3VuaWFwcC1jbGkvbm9kZV9tb2R1bGVzL0BkY2xvdWRpby92dWUtY2xpLXBsdWdpbi11bmkvcGFja2FnZXMvd2VicGFjay1wcmVwcm9jZXNzLWxvYWRlci9pbmRleC5qcz8/cmVmLS01LTEhLi4vLi4vLi4vLi4vSEJ1aWxkZXJYL3BsdWdpbnMvdW5pYXBwLWNsaS9ub2RlX21vZHVsZXMvQGRjbG91ZGlvL3Z1ZS1jbGktcGx1Z2luLXVuaS9wYWNrYWdlcy92dWUtbG9hZGVyL2xpYi9pbmRleC5qcz8/dnVlLWxvYWRlci1vcHRpb25zIS4vY2FtZXJhLm52dWU/dnVlJnR5cGU9c2NyaXB0Jmxhbmc9anMmbXBUeXBlPXBhZ2VcIiJdLCJzb3VyY2VSb290IjoiIn0=\n//# sourceURL=webpack-internal:///8\n");

/***/ }),
/* 9 */
/*!******************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib??ref--5-0!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--5-1!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=script&lang=js&mpType=page ***!
  \******************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";
eval("\n\nObject.defineProperty(exports, \"__esModule\", {\n  value: true\n});\nexports.default = void 0;\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n//\n\nvar _this = null;\nvar _default = {\n  data: function data() {\n    return {\n      dotype: 'idphoto',\n      //操作类型\n      message: '',\n      //提示\n      aspect: '2:3',\n      //比例\n      cameraWidth: '',\n      //相机画面宽度\n      cameraHeight: '',\n      //相机画面宽度\n      windowWidth: '',\n      //屏幕可用宽度\n      windowHeight: '',\n      //屏幕可用高度\n      camerastate: false,\n      //相机准备好了\n      livePusher: null,\n      //流视频对象\n      snapshotsrc: null //快照\n    };\n  },\n  onLoad: function onLoad(e) {\n    _this = this;\n    if (e.dotype != undefined) this.dotype = e.dotype;\n    this.initCamera();\n  },\n  onReady: function onReady() {\n    this.livePusher = uni.createLivePusherContext('livePusher', this);\n    this.startPreview(); //开启预览并设置摄像头\n    this.poenCarme();\n  },\n  methods: {\n    //轮询打开\n    poenCarme: function poenCarme() {\n      if (plus.os.name == 'Android') {\n        this.poenCarmeInterval = setInterval(function () {\n          if (!_this.camerastate) _this.startPreview();\n        }, 2500);\n      }\n    },\n    //初始化相机\n    initCamera: function initCamera() {\n      uni.getSystemInfo({\n        success: function success(res) {\n          _this.windowWidth = res.windowWidth;\n          _this.windowHeight = res.windowHeight;\n          _this.cameraWidth = res.windowWidth;\n          _this.cameraHeight = res.windowWidth * 1.5;\n        }\n      });\n    },\n    //开始预览\n    startPreview: function startPreview() {\n      this.livePusher.startPreview({\n        success: function success(a) {}\n      });\n    },\n    //停止预览\n    stopPreview: function stopPreview() {\n      this.livePusher.stopPreview({\n        success: function success(a) {\n          _this.camerastate = false; //标记相机未启动\n        }\n      });\n    },\n    //状态\n    statechange: function statechange(e) {\n      //状态改变\n      if (e.detail.code == 1007) {\n        _this.camerastate = true;\n      } else if (e.detail.code == -1301) {\n        _this.camerastate = false;\n      }\n    },\n    //返回\n    back: function back() {\n      uni.navigateBack();\n    },\n    //抓拍\n    snapshot: function snapshot() {\n      this.livePusher.snapshot({\n        success: function success(e) {\n          _this.snapshotsrc = e.message.tempImagePath;\n          _this.stopPreview();\n          _this.setImage();\n          uni.navigateBack();\n        }\n      });\n    },\n    //反转\n    flip: function flip() {\n      this.livePusher.switchCamera();\n    },\n    //设置\n    setImage: function setImage() {\n      var pages = getCurrentPages();\n      var prevPage = pages[pages.length - 2]; //上一个页面\n      prevPage.$vm.setImage({\n        path: _this.snapshotsrc,\n        dotype: this.dotype\n      });\n    }\n  }\n};\nexports.default = _default;//# sourceURL=[module]\n//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInVuaS1hcHA6Ly8vcGFnZXMvc2Nhbi9jYW1lcmEubnZ1ZSJdLCJuYW1lcyI6WyJkYXRhIiwiZG90eXBlIiwibWVzc2FnZSIsImFzcGVjdCIsImNhbWVyYVdpZHRoIiwiY2FtZXJhSGVpZ2h0Iiwid2luZG93V2lkdGgiLCJ3aW5kb3dIZWlnaHQiLCJjYW1lcmFzdGF0ZSIsImxpdmVQdXNoZXIiLCJzbmFwc2hvdHNyYyIsIm9uTG9hZCIsIl90aGlzIiwib25SZWFkeSIsIm1ldGhvZHMiLCJwb2VuQ2FybWUiLCJpbml0Q2FtZXJhIiwidW5pIiwic3VjY2VzcyIsInN0YXJ0UHJldmlldyIsInN0b3BQcmV2aWV3Iiwic3RhdGVjaGFuZ2UiLCJiYWNrIiwic25hcHNob3QiLCJmbGlwIiwic2V0SW1hZ2UiLCJwcmV2UGFnZSIsInBhdGgiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQW1EQTtBQUFBLGVBQ0E7RUFDQUE7SUFDQTtNQUNBQztNQUFBO01BQ0FDO01BQUE7TUFDQUM7TUFBQTtNQUNBQztNQUFBO01BQ0FDO01BQUE7TUFDQUM7TUFBQTtNQUNBQztNQUFBO01BQ0FDO01BQUE7TUFDQUM7TUFBQTtNQUNBQztJQUNBO0VBQ0E7RUFDQUM7SUFDQUM7SUFDQTtJQUNBO0VBQ0E7RUFDQUM7SUFDQTtJQUNBO0lBQ0E7RUFDQTtFQUNBQztJQUNBO0lBQ0FDO01BRUE7UUFDQTtVQUNBO1FBQ0E7TUFDQTtJQUVBO0lBRUE7SUFDQUM7TUFDQUM7UUFDQUM7VUFDQU47VUFDQUE7VUFDQUE7VUFDQUE7UUFDQTtNQUNBO0lBQ0E7SUFFQTtJQUNBTztNQUNBO1FBQ0FELDhCQUNBO01BQ0E7SUFDQTtJQUNBO0lBQ0FFO01BQ0E7UUFDQUY7VUFDQU47UUFDQTtNQUNBO0lBQ0E7SUFFQTtJQUNBUztNQUNBO01BQ0E7UUFDQVQ7TUFDQTtRQUNBQTtNQUNBO0lBQ0E7SUFFQTtJQUNBVTtNQUNBTDtJQUNBO0lBRUE7SUFDQU07TUFDQTtRQUNBTDtVQUNBTjtVQUNBQTtVQUNBQTtVQUNBSztRQUNBO01BQ0E7SUFDQTtJQUVBO0lBQ0FPO01BQ0E7SUFDQTtJQUVBO0lBQ0FDO01BQ0E7TUFDQTtNQUNBQztRQUFBQztRQUFBMUI7TUFBQTtJQUNBO0VBQ0E7QUFDQTtBQUFBIiwiZmlsZSI6IjkuanMiLCJzb3VyY2VzQ29udGVudCI6WyI8dGVtcGxhdGU+XHJcblx0PHZpZXcgY2xhc3M9XCJtdXNpYy1jYW1lcmFcIiA6c3R5bGU9XCJ7IHdpZHRoOiB3aW5kb3dXaWR0aCwgaGVpZ2h0OiB3aW5kb3dIZWlnaHQgfVwiPlxyXG5cdFx0PHZpZXcgY2xhc3M9XCJwcmV2aWV3XCIgOnN0eWxlPVwieyB3aWR0aDogd2luZG93V2lkdGgsIGhlaWdodDogd2luZG93SGVpZ2h0IC0gODAgfVwiPlxyXG5cdFx0XHQ8bGl2ZS1wdXNoZXJcclxuXHRcdFx0XHRpZD1cImxpdmVQdXNoZXJcIlxyXG5cdFx0XHRcdHJlZj1cImxpdmVQdXNoZXJcIlxyXG5cdFx0XHRcdGNsYXNzPVwibGl2ZVB1c2hlclwiXHJcblx0XHRcdFx0bW9kZT1cIkZIRFwiXHJcblx0XHRcdFx0YmVhdXR5PVwiMVwiXHJcblx0XHRcdFx0d2hpdGVuZXNzPVwiMFwiXHJcblx0XHRcdFx0OmFzcGVjdD1cImFzcGVjdFwiXHJcblx0XHRcdFx0bWluLWJpdHJhdGU9XCIxMDAwXCJcclxuXHRcdFx0XHRhdWRpby1xdWFsaXR5PVwiMTZLSHpcIlxyXG5cdFx0XHRcdGRldmljZS1wb3NpdGlvbj1cImZyb250XCJcclxuXHRcdFx0XHQ6YXV0by1mb2N1cz1cInRydWVcIlxyXG5cdFx0XHRcdDptdXRlZD1cInRydWVcIlxyXG5cdFx0XHRcdDplbmFibGUtY2FtZXJhPVwidHJ1ZVwiXHJcblx0XHRcdFx0OmVuYWJsZS1taWM9XCJmYWxzZVwiXHJcblx0XHRcdFx0Onpvb209XCJmYWxzZVwiXHJcblx0XHRcdFx0QHN0YXRlY2hhbmdlPVwic3RhdGVjaGFuZ2VcIlxyXG5cdFx0XHRcdDpzdHlsZT1cInsgd2lkdGg6IGNhbWVyYVdpZHRoLCBoZWlnaHQ6IGNhbWVyYUhlaWdodCB9XCJcclxuXHRcdFx0PjwvbGl2ZS1wdXNoZXI+XHJcblxyXG5cdFx0XHQ8IS0t5o+Q56S66K+tLS0+XHJcblx0XHRcdDxjb3Zlci12aWV3IGNsYXNzPVwicmVtaW5kXCI+XHJcblx0XHRcdFx0PHRleHQgY2xhc3M9XCJyZW1pbmQtdGV4dFwiIHN0eWxlPVwiXCI+e3sgbWVzc2FnZSB9fTwvdGV4dD5cclxuXHRcdFx0PC9jb3Zlci12aWV3PlxyXG5cclxuXHRcdFx0PCEtLei+heWKqee6vy0tPlxyXG5cdFx0XHQ8Y292ZXItdmlldyBjbGFzcz1cIm91dGxpbmUtYm94XCIgOnN0eWxlPVwieyB3aWR0aDogd2luZG93V2lkdGgsIGhlaWdodDogd2luZG93SGVpZ2h0IC0gMjU1IH1cIj5cclxuXHRcdFx0XHQ8Y292ZXItaW1hZ2UgY2xhc3M9XCJvdXRsaW5lLWltZ1wiIHNyYz1cIi9zdGF0aWMvb3V0bGluZS9pZHBob3Rvc2tpbi5wbmdcIiBzdHlsZT1cIlwiPjwvY292ZXItaW1hZ2U+XHJcblx0XHRcdDwvY292ZXItdmlldz5cclxuXHRcdDwvdmlldz5cclxuXHJcblx0XHQ8dmlldyBjbGFzcz1cIm1lbnVcIj5cclxuXHRcdFx0PCEtLeW6lemDqOiPnOWNleWMuuWfn+iDjOaZry0tPlxyXG5cdFx0XHQ8Y292ZXItaW1hZ2UgY2xhc3M9XCJtZW51LW1hc2tcIiBzcmM9XCIvc3RhdGljL2Jhci5wbmdcIj48L2NvdmVyLWltYWdlPlxyXG5cclxuXHRcdFx0PCEtLei/lOWbnumUri0tPlxyXG5cdFx0XHQ8Y292ZXItaW1hZ2UgY2xhc3M9XCJtZW51LWJhY2tcIiBAdGFwPVwiYmFja1wiIHNyYz1cIi9zdGF0aWMvYmFjay5wbmdcIj48L2NvdmVyLWltYWdlPlxyXG5cclxuXHRcdFx0PCEtLeW/q+mXqOmUri0tPlxyXG5cdFx0XHQ8Y292ZXItaW1hZ2UgY2xhc3M9XCJtZW51LXNuYXBzaG90XCIgQHRhcD1cInNuYXBzaG90XCIgc3JjPVwiL3N0YXRpYy9zaHV0dGVyLnBuZ1wiPjwvY292ZXItaW1hZ2U+XHJcblxyXG5cdFx0XHQ8IS0t5Y+N6L2s6ZSuLS0+XHJcblx0XHRcdDxjb3Zlci1pbWFnZSBjbGFzcz1cIm1lbnUtZmxpcFwiIEB0YXA9XCJmbGlwXCIgc3JjPVwiL3N0YXRpYy9mbGlwLnBuZ1wiPjwvY292ZXItaW1hZ2U+XHJcblx0XHQ8L3ZpZXc+XHJcblx0PC92aWV3PlxyXG48L3RlbXBsYXRlPlxyXG5cclxuPHNjcmlwdD5cclxubGV0IF90aGlzID0gbnVsbDtcclxuZXhwb3J0IGRlZmF1bHQge1xyXG5cdGRhdGEoKSB7XHJcblx0XHRyZXR1cm4ge1xyXG5cdFx0XHRkb3R5cGU6ICdpZHBob3RvJywgLy/mk43kvZznsbvlnotcclxuXHRcdFx0bWVzc2FnZTogJycsIC8v5o+Q56S6XHJcblx0XHRcdGFzcGVjdDogJzI6MycsIC8v5q+U5L6LXHJcblx0XHRcdGNhbWVyYVdpZHRoOiAnJywgLy/nm7jmnLrnlLvpnaLlrr3luqZcclxuXHRcdFx0Y2FtZXJhSGVpZ2h0OiAnJywgLy/nm7jmnLrnlLvpnaLlrr3luqZcclxuXHRcdFx0d2luZG93V2lkdGg6ICcnLCAvL+Wxj+W5leWPr+eUqOWuveW6plxyXG5cdFx0XHR3aW5kb3dIZWlnaHQ6ICcnLCAvL+Wxj+W5leWPr+eUqOmrmOW6plxyXG5cdFx0XHRjYW1lcmFzdGF0ZTogZmFsc2UsIC8v55u45py65YeG5aSH5aW95LqGXHJcblx0XHRcdGxpdmVQdXNoZXI6IG51bGwsIC8v5rWB6KeG6aKR5a+56LGhXHJcblx0XHRcdHNuYXBzaG90c3JjOiBudWxsIC8v5b+r54WnXHJcblx0XHR9O1xyXG5cdH0sXHJcblx0b25Mb2FkKGUpIHtcclxuXHRcdF90aGlzID0gdGhpcztcclxuXHRcdGlmIChlLmRvdHlwZSAhPSB1bmRlZmluZWQpIHRoaXMuZG90eXBlID0gZS5kb3R5cGU7XHJcblx0XHR0aGlzLmluaXRDYW1lcmEoKTtcclxuXHR9LFxyXG5cdG9uUmVhZHkoKSB7XHJcblx0XHR0aGlzLmxpdmVQdXNoZXIgPSB1bmkuY3JlYXRlTGl2ZVB1c2hlckNvbnRleHQoJ2xpdmVQdXNoZXInLCB0aGlzKTtcclxuXHRcdHRoaXMuc3RhcnRQcmV2aWV3KCk7IC8v5byA5ZCv6aKE6KeI5bm26K6+572u5pGE5YOP5aS0XHJcblx0XHR0aGlzLnBvZW5DYXJtZSgpO1xyXG5cdH0sXHJcblx0bWV0aG9kczoge1xyXG5cdFx0Ly/ova7or6LmiZPlvIBcclxuXHRcdHBvZW5DYXJtZSgpIHtcclxuXHRcdFx0Ly8jaWZkZWYgQVBQLVBMVVNcclxuXHRcdFx0aWYgKHBsdXMub3MubmFtZSA9PSAnQW5kcm9pZCcpIHtcclxuXHRcdFx0XHR0aGlzLnBvZW5DYXJtZUludGVydmFsID0gc2V0SW50ZXJ2YWwoZnVuY3Rpb24oKSB7XHJcblx0XHRcdFx0XHRpZiAoIV90aGlzLmNhbWVyYXN0YXRlKSBfdGhpcy5zdGFydFByZXZpZXcoKTtcclxuXHRcdFx0XHR9LCAyNTAwKTtcclxuXHRcdFx0fVxyXG5cdFx0XHQvLyNlbmRpZlxyXG5cdFx0fSxcclxuXHJcblx0XHQvL+WIneWni+WMluebuOaculxyXG5cdFx0aW5pdENhbWVyYSgpIHtcclxuXHRcdFx0dW5pLmdldFN5c3RlbUluZm8oe1xyXG5cdFx0XHRcdHN1Y2Nlc3M6IGZ1bmN0aW9uKHJlcykge1xyXG5cdFx0XHRcdFx0X3RoaXMud2luZG93V2lkdGggPSByZXMud2luZG93V2lkdGg7XHJcblx0XHRcdFx0XHRfdGhpcy53aW5kb3dIZWlnaHQgPSByZXMud2luZG93SGVpZ2h0O1xyXG5cdFx0XHRcdFx0X3RoaXMuY2FtZXJhV2lkdGggPSByZXMud2luZG93V2lkdGg7XHJcblx0XHRcdFx0XHRfdGhpcy5jYW1lcmFIZWlnaHQgPSByZXMud2luZG93V2lkdGggKiAxLjU7XHJcblx0XHRcdFx0fVxyXG5cdFx0XHR9KTtcclxuXHRcdH0sXHJcblxyXG5cdFx0Ly/lvIDlp4vpooTop4hcclxuXHRcdHN0YXJ0UHJldmlldygpIHtcclxuXHRcdFx0dGhpcy5saXZlUHVzaGVyLnN0YXJ0UHJldmlldyh7XHJcblx0XHRcdFx0c3VjY2VzczogYSA9PiB7XHJcblx0XHRcdFx0fVxyXG5cdFx0XHR9KTtcclxuXHRcdH0sXHJcblx0XHQvL+WBnOatoumihOiniFxyXG5cdFx0c3RvcFByZXZpZXcoKSB7XHJcblx0XHRcdHRoaXMubGl2ZVB1c2hlci5zdG9wUHJldmlldyh7XHJcblx0XHRcdFx0c3VjY2VzczogYSA9PiB7XHJcblx0XHRcdFx0XHRfdGhpcy5jYW1lcmFzdGF0ZSA9IGZhbHNlOyAvL+agh+iusOebuOacuuacquWQr+WKqFxyXG5cdFx0XHRcdH1cclxuXHRcdFx0fSk7XHJcblx0XHR9LFxyXG5cclxuXHRcdC8v54q25oCBXHJcblx0XHRzdGF0ZWNoYW5nZShlKSB7XHJcblx0XHRcdC8v54q25oCB5pS55Y+YXHJcblx0XHRcdGlmIChlLmRldGFpbC5jb2RlID09IDEwMDcpIHtcclxuXHRcdFx0XHRfdGhpcy5jYW1lcmFzdGF0ZSA9IHRydWU7XHJcblx0XHRcdH0gZWxzZSBpZiAoZS5kZXRhaWwuY29kZSA9PSAtMTMwMSkge1xyXG5cdFx0XHRcdF90aGlzLmNhbWVyYXN0YXRlID0gZmFsc2U7XHJcblx0XHRcdH1cclxuXHRcdH0sXHJcblxyXG5cdFx0Ly/ov5Tlm55cclxuXHRcdGJhY2soKSB7XHJcblx0XHRcdHVuaS5uYXZpZ2F0ZUJhY2soKTtcclxuXHRcdH0sXHJcblxyXG5cdFx0Ly/mipPmi41cclxuXHRcdHNuYXBzaG90KCkge1xyXG5cdFx0XHR0aGlzLmxpdmVQdXNoZXIuc25hcHNob3Qoe1xyXG5cdFx0XHRcdHN1Y2Nlc3M6IGUgPT4ge1xyXG5cdFx0XHRcdFx0X3RoaXMuc25hcHNob3RzcmMgPSBlLm1lc3NhZ2UudGVtcEltYWdlUGF0aDtcclxuXHRcdFx0XHRcdF90aGlzLnN0b3BQcmV2aWV3KCk7XHJcblx0XHRcdFx0XHRfdGhpcy5zZXRJbWFnZSgpO1xyXG5cdFx0XHRcdFx0dW5pLm5hdmlnYXRlQmFjaygpO1xyXG5cdFx0XHRcdH1cclxuXHRcdFx0fSk7XHJcblx0XHR9LFxyXG5cclxuXHRcdC8v5Y+N6L2sXHJcblx0XHRmbGlwKCkge1xyXG5cdFx0XHR0aGlzLmxpdmVQdXNoZXIuc3dpdGNoQ2FtZXJhKCk7XHJcblx0XHR9LFxyXG5cclxuXHRcdC8v6K6+572uXHJcblx0XHRzZXRJbWFnZSgpIHtcclxuXHRcdFx0bGV0IHBhZ2VzID0gZ2V0Q3VycmVudFBhZ2VzKCk7XHJcblx0XHRcdGxldCBwcmV2UGFnZSA9IHBhZ2VzW3BhZ2VzLmxlbmd0aCAtIDJdOyAvL+S4iuS4gOS4qumhtemdolxyXG5cdFx0XHRwcmV2UGFnZS4kdm0uc2V0SW1hZ2UoeyBwYXRoOiBfdGhpcy5zbmFwc2hvdHNyYywgZG90eXBlOiB0aGlzLmRvdHlwZSB9KTtcclxuXHRcdH1cclxuXHR9XHJcbn07XHJcbjwvc2NyaXB0PlxyXG5cclxuPHN0eWxlIGxhbmc9XCJzY3NzXCI+XHJcbi5tdXNpYy1jYW1lcmEge1xyXG5cdHotaW5kZXg6IDk5OTk5O1xyXG5cdC5wcmV2aWV3IHtcclxuXHRcdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG5cdFx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHRcdC5vdXRsaW5lLWJveCB7XHJcblx0XHRcdHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuXHRcdFx0dG9wOiAwO1xyXG5cdFx0XHRsZWZ0OiAwO1xyXG5cdFx0XHRib3R0b206IDA7XHJcblx0XHRcdHotaW5kZXg6IDk5O1xyXG5cdFx0XHRhbGlnbi1pdGVtczogY2VudGVyO1xyXG5cdFx0XHRqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuXHRcdFx0Lm91dGxpbmUtaW1nIHtcclxuXHRcdFx0XHR3aWR0aDogNzUwcnB4O1xyXG5cdFx0XHRcdGhlaWdodDogMTEyNXJweDtcclxuXHRcdFx0fVxyXG5cdFx0fVxyXG5cdFx0LnJlbWluZCB7XHJcblx0XHRcdHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuXHRcdFx0dG9wOiA4ODBycHg7XHJcblx0XHRcdHdpZHRoOiA3NTBycHg7XHJcblx0XHRcdHotaW5kZXg6IDEwMDtcclxuXHRcdFx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHRcdFx0anVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcblx0XHRcdC5yZW1pbmQtdGV4dCB7XHJcblx0XHRcdFx0Y29sb3I6ICNkZGRkZGQ7XHJcblx0XHRcdFx0Zm9udC13ZWlnaHQ6IGJvbGQ7XHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9XHJcblx0Lm1lbnUge1xyXG5cdFx0cG9zaXRpb246IGFic29sdXRlO1xyXG5cdFx0bGVmdDogMDtcclxuXHRcdGJvdHRvbTogMDtcclxuXHRcdHdpZHRoOiA3NTBycHg7XHJcblx0XHRoZWlnaHQ6IDU1MHJweDtcclxuXHRcdHotaW5kZXg6IDk4O1xyXG5cdFx0YWxpZ24taXRlbXM6IGNlbnRlcjtcclxuXHRcdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG5cdFx0Lm1lbnUtbWFzayB7XHJcblx0XHRcdHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuXHRcdFx0bGVmdDogMDtcclxuXHRcdFx0Ym90dG9tOiAwO1xyXG5cdFx0XHR3aWR0aDogNzUwcnB4O1xyXG5cdFx0XHRoZWlnaHQ6IDU1MHJweDtcclxuXHRcdFx0ei1pbmRleDogOTg7XHJcblx0XHR9XHJcblx0XHQubWVudS1iYWNrIHtcclxuXHRcdFx0cG9zaXRpb246IGFic29sdXRlO1xyXG5cdFx0XHRsZWZ0OiA2MHJweDtcclxuXHRcdFx0Ym90dG9tOiAxMDBycHg7XHJcblx0XHRcdHdpZHRoOiA4MHJweDtcclxuXHRcdFx0aGVpZ2h0OiA4MHJweDtcclxuXHRcdFx0ei1pbmRleDogOTk7XHJcblx0XHRcdGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcblx0XHRcdGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG5cdFx0fVxyXG5cdFx0Lm1lbnUtc25hcHNob3Qge1xyXG5cdFx0XHR3aWR0aDogMTMwcnB4O1xyXG5cdFx0XHRoZWlnaHQ6IDEzMHJweDtcclxuXHRcdFx0ei1pbmRleDogOTk7XHJcblx0XHR9XHJcblx0XHQubWVudS1mbGlwIHtcclxuXHRcdFx0cG9zaXRpb246IGFic29sdXRlO1xyXG5cdFx0XHRyaWdodDogNjBycHg7XHJcblx0XHRcdGJvdHRvbTogMTAwcnB4O1xyXG5cdFx0XHR3aWR0aDogODBycHg7XHJcblx0XHRcdGhlaWdodDogODBycHg7XHJcblx0XHRcdHotaW5kZXg6IDk5O1xyXG5cdFx0XHRhbGlnbi1pdGVtczogY2VudGVyO1xyXG5cdFx0XHRqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuXHRcdH1cclxuXHR9XHJcbn1cclxuPC9zdHlsZT5cclxuIl0sInNvdXJjZVJvb3QiOiIifQ==\n//# sourceURL=webpack-internal:///9\n");

/***/ }),
/* 10 */
/*!************************************************************************************************************!*\
  !*** D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=style&index=0&lang=scss&mpType=page ***!
  \************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--11-oneOf-0-1!../../../../HBuilderX/plugins/uniapp-cli/node_modules/postcss-loader/src??ref--11-oneOf-0-2!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/sass-loader/dist/cjs.js??ref--11-oneOf-0-3!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--11-oneOf-0-4!../../../../HBuilderX/plugins/uniapp-cli/node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!./camera.nvue?vue&type=style&index=0&lang=scss&mpType=page */ 11);
/* harmony import */ var _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0__);
/* harmony reexport (unknown) */ for(var __WEBPACK_IMPORT_KEY__ in _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0__) if(["default"].indexOf(__WEBPACK_IMPORT_KEY__) < 0) (function(key) { __webpack_require__.d(__webpack_exports__, key, function() { return _HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0__[key]; }) }(__WEBPACK_IMPORT_KEY__));
 /* harmony default export */ __webpack_exports__["default"] = (_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_hbuilderx_packages_webpack_uni_nvue_loader_lib_style_js_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_1_HBuilderX_plugins_uniapp_cli_node_modules_postcss_loader_src_index_js_ref_11_oneOf_0_2_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_sass_loader_dist_cjs_js_ref_11_oneOf_0_3_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_webpack_preprocess_loader_index_js_ref_11_oneOf_0_4_HBuilderX_plugins_uniapp_cli_node_modules_dcloudio_vue_cli_plugin_uni_packages_vue_loader_lib_index_js_vue_loader_options_camera_nvue_vue_type_style_index_0_lang_scss_mpType_page__WEBPACK_IMPORTED_MODULE_0___default.a); 

/***/ }),
/* 11 */
/*!*************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-hbuilderx/packages/webpack-uni-nvue-loader/lib/style.js!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--11-oneOf-0-1!./node_modules/postcss-loader/src??ref--11-oneOf-0-2!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/sass-loader/dist/cjs.js??ref--11-oneOf-0-3!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/webpack-preprocess-loader??ref--11-oneOf-0-4!./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib??vue-loader-options!D:/GitHub_Project/music-frontend/pages/scan/camera.nvue?vue&type=style&index=0&lang=scss&mpType=page ***!
  \*************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = {
  ".music-camera": {
    "": {
      "zIndex": [
        99999,
        0,
        0,
        16
      ]
    }
  },
  ".preview": {
    ".music-camera ": {
      "justifyContent": [
        "center",
        0,
        1,
        17
      ],
      "alignItems": [
        "center",
        0,
        1,
        17
      ]
    }
  },
  ".outline-box": {
    ".music-camera .preview ": {
      "position": [
        "absolute",
        0,
        2,
        18
      ],
      "top": [
        0,
        0,
        2,
        18
      ],
      "left": [
        0,
        0,
        2,
        18
      ],
      "bottom": [
        0,
        0,
        2,
        18
      ],
      "zIndex": [
        99,
        0,
        2,
        18
      ],
      "alignItems": [
        "center",
        0,
        2,
        18
      ],
      "justifyContent": [
        "center",
        0,
        2,
        18
      ]
    }
  },
  ".outline-img": {
    ".music-camera .preview .outline-box ": {
      "width": [
        "750rpx",
        0,
        3,
        19
      ],
      "height": [
        "1125rpx",
        0,
        3,
        19
      ]
    }
  },
  ".remind": {
    ".music-camera .preview ": {
      "position": [
        "absolute",
        0,
        2,
        20
      ],
      "top": [
        "880rpx",
        0,
        2,
        20
      ],
      "width": [
        "750rpx",
        0,
        2,
        20
      ],
      "zIndex": [
        100,
        0,
        2,
        20
      ],
      "alignItems": [
        "center",
        0,
        2,
        20
      ],
      "justifyContent": [
        "center",
        0,
        2,
        20
      ]
    }
  },
  ".remind-text": {
    ".music-camera .preview .remind ": {
      "color": [
        "#dddddd",
        0,
        3,
        21
      ],
      "fontWeight": [
        "bold",
        0,
        3,
        21
      ]
    }
  },
  ".menu": {
    ".music-camera ": {
      "position": [
        "absolute",
        0,
        1,
        22
      ],
      "left": [
        0,
        0,
        1,
        22
      ],
      "bottom": [
        0,
        0,
        1,
        22
      ],
      "width": [
        "750rpx",
        0,
        1,
        22
      ],
      "height": [
        "550rpx",
        0,
        1,
        22
      ],
      "zIndex": [
        98,
        0,
        1,
        22
      ],
      "alignItems": [
        "center",
        0,
        1,
        22
      ],
      "justifyContent": [
        "center",
        0,
        1,
        22
      ]
    }
  },
  ".menu-mask": {
    ".music-camera .menu ": {
      "position": [
        "absolute",
        0,
        2,
        23
      ],
      "left": [
        0,
        0,
        2,
        23
      ],
      "bottom": [
        0,
        0,
        2,
        23
      ],
      "width": [
        "750rpx",
        0,
        2,
        23
      ],
      "height": [
        "550rpx",
        0,
        2,
        23
      ],
      "zIndex": [
        98,
        0,
        2,
        23
      ]
    }
  },
  ".menu-back": {
    ".music-camera .menu ": {
      "position": [
        "absolute",
        0,
        2,
        24
      ],
      "left": [
        "60rpx",
        0,
        2,
        24
      ],
      "bottom": [
        "100rpx",
        0,
        2,
        24
      ],
      "width": [
        "80rpx",
        0,
        2,
        24
      ],
      "height": [
        "80rpx",
        0,
        2,
        24
      ],
      "zIndex": [
        99,
        0,
        2,
        24
      ],
      "alignItems": [
        "center",
        0,
        2,
        24
      ],
      "justifyContent": [
        "center",
        0,
        2,
        24
      ]
    }
  },
  ".menu-snapshot": {
    ".music-camera .menu ": {
      "width": [
        "130rpx",
        0,
        2,
        25
      ],
      "height": [
        "130rpx",
        0,
        2,
        25
      ],
      "zIndex": [
        99,
        0,
        2,
        25
      ]
    }
  },
  ".menu-flip": {
    ".music-camera .menu ": {
      "position": [
        "absolute",
        0,
        2,
        26
      ],
      "right": [
        "60rpx",
        0,
        2,
        26
      ],
      "bottom": [
        "100rpx",
        0,
        2,
        26
      ],
      "width": [
        "80rpx",
        0,
        2,
        26
      ],
      "height": [
        "80rpx",
        0,
        2,
        26
      ],
      "zIndex": [
        99,
        0,
        2,
        26
      ],
      "alignItems": [
        "center",
        0,
        2,
        26
      ],
      "justifyContent": [
        "center",
        0,
        2,
        26
      ]
    }
  },
  "@VERSION": 2
}

/***/ }),
/* 12 */
/*!**********************************************************************************************************!*\
  !*** ./node_modules/@dcloudio/vue-cli-plugin-uni/packages/vue-loader/lib/runtime/componentNormalizer.js ***!
  \**********************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return normalizeComponent; });
/* globals __VUE_SSR_CONTEXT__ */

// IMPORTANT: Do NOT use ES2015 features in this file (except for modules).
// This module is a runtime utility for cleaner component module output and will
// be included in the final webpack user bundle.

function normalizeComponent (
  scriptExports,
  render,
  staticRenderFns,
  functionalTemplate,
  injectStyles,
  scopeId,
  moduleIdentifier, /* server only */
  shadowMode, /* vue-cli only */
  components, // fixed by xxxxxx auto components
  renderjs // fixed by xxxxxx renderjs
) {
  // Vue.extend constructor export interop
  var options = typeof scriptExports === 'function'
    ? scriptExports.options
    : scriptExports

  // fixed by xxxxxx auto components
  if (components) {
    if (!options.components) {
      options.components = {}
    }
    var hasOwn = Object.prototype.hasOwnProperty
    for (var name in components) {
      if (hasOwn.call(components, name) && !hasOwn.call(options.components, name)) {
        options.components[name] = components[name]
      }
    }
  }
  // fixed by xxxxxx renderjs
  if (renderjs) {
    if(typeof renderjs.beforeCreate === 'function'){
			renderjs.beforeCreate = [renderjs.beforeCreate]
		}
    (renderjs.beforeCreate || (renderjs.beforeCreate = [])).unshift(function() {
      this[renderjs.__module] = this
    });
    (options.mixins || (options.mixins = [])).push(renderjs)
  }

  // render functions
  if (render) {
    options.render = render
    options.staticRenderFns = staticRenderFns
    options._compiled = true
  }

  // functional template
  if (functionalTemplate) {
    options.functional = true
  }

  // scopedId
  if (scopeId) {
    options._scopeId = 'data-v-' + scopeId
  }

  var hook
  if (moduleIdentifier) { // server build
    hook = function (context) {
      // 2.3 injection
      context =
        context || // cached call
        (this.$vnode && this.$vnode.ssrContext) || // stateful
        (this.parent && this.parent.$vnode && this.parent.$vnode.ssrContext) // functional
      // 2.2 with runInNewContext: true
      if (!context && typeof __VUE_SSR_CONTEXT__ !== 'undefined') {
        context = __VUE_SSR_CONTEXT__
      }
      // inject component styles
      if (injectStyles) {
        injectStyles.call(this, context)
      }
      // register component module identifier for async chunk inferrence
      if (context && context._registeredComponents) {
        context._registeredComponents.add(moduleIdentifier)
      }
    }
    // used by ssr in case component is cached and beforeCreate
    // never gets called
    options._ssrRegister = hook
  } else if (injectStyles) {
    hook = shadowMode
      ? function () { injectStyles.call(this, this.$root.$options.shadowRoot) }
      : injectStyles
  }

  if (hook) {
    if (options.functional) {
      // for template-only hot-reload because in that case the render fn doesn't
      // go through the normalizer
      options._injectStyles = hook
      // register for functioal component in vue file
      var originalRender = options.render
      options.render = function renderWithStyleInjection (h, context) {
        hook.call(context)
        return originalRender(h, context)
      }
    } else {
      // inject component registration as beforeCreate hook
      var existing = options.beforeCreate
      options.beforeCreate = existing
        ? [].concat(existing, hook)
        : [hook]
    }
  }

  return {
    exports: scriptExports,
    options: options
  }
}


/***/ })
/******/ ]);
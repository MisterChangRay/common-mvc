webpackJsonp(["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app.component.css":
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<header-component></header-component>\r\n\r\n<div class=\"container-fluid\">\r\n    <div class=\"row\">\r\n\t\t<nav class=\"col-md-2 d-none d-md-block bg-light sidebar\">\r\n\t\t\t<sider-component (showApiEvent)=\"main.showApiDescription($event)\"></sider-component>\r\n\t\t</nav>\r\n        <main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">\r\n\t\t\t<main-component #main></main-component>\r\n        </main>\r\n    </div>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'app';
    }
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__("./src/app/app.component.html"),
            styles: [__webpack_require__("./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("./node_modules/@angular/platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("./node_modules/@angular/forms/esm5/forms.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__("./node_modules/@angular/http/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__common_ApiFilter__ = __webpack_require__("./src/app/common/ApiFilter.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__app_component__ = __webpack_require__("./src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__header_header_component__ = __webpack_require__("./src/app/header/header.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__main_main_component__ = __webpack_require__("./src/app/main/main.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__sider_sider_component__ = __webpack_require__("./src/app/sider/sider.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__common_apiService__ = __webpack_require__("./src/app/common/apiService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__common_myHttpService__ = __webpack_require__("./src/app/common/myHttpService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};












var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["E" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_5__common_ApiFilter__["a" /* ApiFilter */],
                __WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_7__header_header_component__["a" /* HeaderComponent */],
                __WEBPACK_IMPORTED_MODULE_9__sider_sider_component__["a" /* SiderComponent */],
                __WEBPACK_IMPORTED_MODULE_8__main_main_component__["a" /* MainComponent */],
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_http__["b" /* HttpModule */],
                __WEBPACK_IMPORTED_MODULE_4__angular_common_http__["b" /* HttpClientModule */]
            ],
            providers: [__WEBPACK_IMPORTED_MODULE_10__common_apiService__["a" /* ApiDocService */], __WEBPACK_IMPORTED_MODULE_11__common_myHttpService__["a" /* MyHttpService */]],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/app/common/ApiFilter.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ApiFilter; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var ApiFilter = /** @class */ (function () {
    function ApiFilter() {
    }
    ApiFilter.prototype.transform = function (items2, filter) {
        if (!items2 || !filter) {
            return items2;
        }
        var items = JSON.parse(JSON.stringify(items2));
        var res = items.filter(function (item) {
            var filterKey = filter.toLocaleLowerCase();
            if (-1 != item.name.toLocaleLowerCase().indexOf(filter.toLocaleLowerCase())
                || -1 != item.description.toLocaleLowerCase().indexOf(filterKey)) {
                return true;
            }
            if (Array.isArray(item.childs)) {
                item.childs = item.childs.filter(function (item) {
                    if (-1 != item.summary.toLocaleLowerCase().indexOf(filterKey)
                        || -1 != item.description.toLocaleLowerCase().indexOf(filterKey)) {
                        return true;
                    }
                });
                return item.childs.length;
            }
        });
        return res;
    };
    ApiFilter = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["O" /* Pipe */])({
            name: 'apiFilter',
            pure: false
        })
    ], ApiFilter);
    return ApiFilter;
}());



/***/ }),

/***/ "./src/app/common/apiService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ApiDocService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("./node_modules/@angular/http/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise__ = __webpack_require__("./node_modules/rxjs/_esm5/add/operator/toPromise.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_toPromise__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ApiDocService = /** @class */ (function () {
    function ApiDocService(http) {
        this.http = http;
    }
    ApiDocService.prototype.parse = function (apis) {
        var result = apis.tags;
        var paths = apis.paths;
        var start = new Date().getTime();
        var _loop_1 = function (key) {
            var _loop_2 = function (tmp) {
                result.forEach(function (tag) {
                    if (Array.isArray(paths[key][tmp].tags)) {
                        paths[key][tmp].tags.forEach(function (tagName2) {
                            if (tag.name == tagName2) {
                                tag.childs = tag.childs || [];
                                tag.childs.push({
                                    path: key,
                                    method: tmp,
                                    params: paths[key][tmp].parameters,
                                    summary: paths[key][tmp].summary,
                                    description: paths[key][tmp].description
                                });
                            }
                        });
                    }
                });
            };
            for (var tmp in apis.paths[key]) {
                _loop_2(tmp);
            }
        };
        for (var key in apis.paths) {
            _loop_1(key);
        }
        return JSON.parse(JSON.stringify(result));
    };
    ApiDocService.prototype.getApis = function () {
        var url = window["appConfigs"]["swaggerApiUrl"];
        return this.http.get(url)
            .toPromise()
            .then(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ApiDocService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only   
        return Promise.reject(error.message || error);
    };
    ApiDocService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]])
    ], ApiDocService);
    return ApiDocService;
}());



/***/ }),

/***/ "./src/app/common/myHttpService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyHttpService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_toPromise__ = __webpack_require__("./node_modules/rxjs/_esm5/add/operator/toPromise.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_toPromise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_toPromise__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("./node_modules/@angular/common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MyHttpService = /** @class */ (function () {
    function MyHttpService(http) {
        this.http = http;
        this.baseUrl = window["appConfigs"]["debugApiUrl"];
        this.methods = { "get": 1, "post": 1, "put": 1, "delete": 1, "patch": 1 };
    }
    MyHttpService.prototype.sendData = function (method, url, header, data) {
        var headers = new __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["c" /* HttpHeaders */]();
        var params = new __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["d" /* HttpParams */]();
        if (null != header && Array.isArray(header)) {
            header.forEach(function (map) {
                headers = headers.set(map.key, map.value);
            });
        }
        if (null != data && Array.isArray(data)) {
            data.forEach(function (map) {
                params = params.set(map.key, map.value);
            });
        }
        if (this.methods[method]) {
            if ("get" == method || "delete" == method) {
                return this.http[method](this.baseUrl + url, { headers: headers, params: params }).toPromise();
            }
            if ("post" == method || "put" == method || "patch" == method) {
                if (headers.get("content-type")) {
                    return this.http[method](this.baseUrl + url, params, { headers: headers }).toPromise();
                }
                else {
                    return this.http[method](this.baseUrl + url, data, { headers: headers }).toPromise();
                }
            }
        }
        else {
            console.log("暂时不支持此方法:" + method);
        }
    };
    MyHttpService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClient */]])
    ], MyHttpService);
    return MyHttpService;
}());



/***/ }),

/***/ "./src/app/header/header.component.html":
/***/ (function(module, exports) {

module.exports = "<nav class=\"navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0\">\r\n    <a class=\"navbar-brand col-sm-3 col-md-2 mr-0\" href=\"#\">简易SwaggerApi文档系统</a>\r\n    \r\n</nav>"

/***/ }),

/***/ "./src/app/header/header.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HeaderComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var HeaderComponent = /** @class */ (function () {
    function HeaderComponent() {
        this.title = 'app';
    }
    HeaderComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'header-component',
            template: __webpack_require__("./src/app/header/header.component.html")
        })
    ], HeaderComponent);
    return HeaderComponent;
}());



/***/ }),

/***/ "./src/app/main/main.component.html":
/***/ (function(module, exports) {

module.exports = "\r\n\r\n<div class=\"card text-center\">\r\n    <div class=\"card-header\">\r\n        <ul class=\"nav nav-tabs card-header-tabs\">\r\n            <li class=\"nav-item\">\r\n                <a class=\"nav-link \" [ngClass]=\"{'active':tagId==0}\" (click)=\"showTag(0)\" href=\"javascript:void(0)\">API</a>\r\n            </li>\r\n            <li class=\"nav-item\">\r\n                <a class=\"nav-link\" [ngClass]=\"{'active':tagId==1}\" (click)=\"showTag(1)\" href=\"javascript:void(0)\">Debug</a>\r\n            </li>\r\n        </ul>\r\n    </div>\r\n  \r\n\r\n    <div class=\"card-body\" [ngClass]=\"{'collapse':tagId==1}\">\r\n    \t<h5 class=\"card-title text-left\">Request</h5>\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-bordered\">\r\n\t\t\t  <tbody>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\" class=\"width200\">Path</th>\r\n\t\t\t      <td class=\"text-left\">{{api.path}}</td>\r\n\t\t\t    </tr>\r\n\t\t\t     <tr>\r\n\t\t\t      <th scope=\"row\">Method</th>\r\n\t\t\t      <td class=\"text-left\" colspan=\"2\">{{api.method}}</td>\r\n\t\t\t    </tr>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\">Content-Type</th>\r\n\t\t\t      <td class=\"text-left\" colspan=\"2\">post</td>\r\n\t\t\t    </tr>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\" class=\"width200\">Description</th>\r\n\t\t\t      <td class=\"text-left\">{{api.description}}</td>\r\n\t\t\t    </tr>\r\n\t\t\t  </tbody>\r\n\t\t\t</table>\r\n        </div>\r\n        <h5 class=\"card-title text-left\">Params</h5>\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-bordered\">\r\n            <thead>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"col\" class=\"width200\">name</th>\r\n\t\t\t      <th scope=\"col\" class=\"width200\">data type</th>\r\n\t\t\t      <th scope=\"col\" class=\"width200\">param type</th>\r\n\t\t\t      <th scope=\"col\" class=\"width200\">required</th>\r\n\t\t\t      <th scope=\"col\">description</th>\r\n\t\t\t    </tr>\r\n\t\t\t  </thead>\r\n\t\t\t  <tbody>\r\n\t\t\t    <tr *ngFor=\"let item of api.params | apiFilter:searchKey;let index = index\">\r\n\t\t\t      <td>{{item.name}}</td>\r\n\t\t\t      <td>\r\n\t\t\t      \t<a  (click)='showObj(parseParam(item))' href=\"javascript:void(0)\" title=\"点击查看详情\">{{parseParam(item).text}}</a>\r\n\t\t\t  \t  </td>\r\n\t\t\t      <td>{{item.in}}</td>\r\n\t\t\t      <td>{{item.required}}</td>\r\n\t\t\t      <td>{{item.description}}</td>\r\n\t\t\t    </tr>\r\n\t\t\t   \r\n\t\t\t  </tbody>\r\n\t\t\t</table>\r\n        </div>\r\n\r\n\r\n        <!-- 暂时不显示response\r\n        <h5 class=\"card-title text-left\">Response</h5>\r\n        <div class=\"table-responsive\">\r\n            <table class=\"table table-bordered\">\r\n\t\t\t  <tbody>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\" class=\"width200\">Path</th>\r\n\t\t\t      <td class=\"text-left\">Description</td>\r\n\t\t\t    </tr>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\" class=\"width200\">Description</th>\r\n\t\t\t      <td class=\"text-left\">Jacob</td>\r\n\t\t\t    </tr>\r\n\t\t\t    <tr>\r\n\t\t\t      <th scope=\"row\">Method</th>\r\n\t\t\t      <td class=\"text-left\" colspan=\"2\">post</td>\r\n\t\t\t    </tr>\r\n\t\t\t  </tbody>\r\n\t\t\t</table>\r\n        </div>\r\n \t\t-->\r\n\r\n       \r\n\r\n    </div>\r\n\r\n      <div class=\"card-body\" [ngClass]=\"{'collapse':tagId==0}\">\r\n      \t<form name=\"debug\">\r\n      \t\r\n\t    \t<h5 class=\"card-title text-left\">Params</h5>\r\n\t        <div class=\"table-responsive\">\r\n\t            <table class=\"table table-bordered\">\r\n\t            <thead>\r\n\t\t\t\t    <tr>\r\n\t\t\t\t      <th scope=\"col\" class=\"width200\">name</th>\r\n\t\t\t\t      <th scope=\"col\" class=\"width300\">value</th>\r\n\t\t\t\t      <th scope=\"col\" class=\"width200\">type</th>\r\n\t\t\t\t      <th scope=\"col\" class=\"\">description</th>\r\n\t\t\t\t    </tr>\r\n\t\t\t\t  </thead>\r\n\t\t\t\t  <tbody>\r\n\t\t\t\t    <tr  *ngFor=\"let item of api.params ;let index = index\">\r\n\t\t\t\t      <td>{{item.name}}</td>\r\n\t\t\t\t      <td>\r\n\t\t\t\t      \t<div class=\"form-group\" [ngClass]=\"{'hide':getShowType(item)!='text'}\">\r\n\t\t\t\t      \t\t<input [(ngModel)]=\"formData[item.name]\" name=\"{{item.name}}\" type=\"text\" class=\"form-control\" placeholder=\"{{item.required ? '必填' : '非必填'}}{{item.type == 'array' ? '多个值用,隔开' : ''}}\" />\r\n\t\t\t\t      \t</div>\r\n\t\t\t\t      \t <div class=\"form-group\" [ngClass]=\"{'hide':getShowType(item)!='textarea'}\">\r\n\t\t\t\t\t\t    <textarea [(ngModel)]=\"formData[item.name]\" name=\"{{item.name + '2'}}\" class=\"form-control\" placeholder=\"{{item.required ? '必填' : '非必填'}}, JSON对象\" rows=\"6\"></textarea>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t      </td>\r\n\t\t\t\t      <td>\r\n\t\t\t\t      \t<a  (click)='showObj(parseParam(item))' href=\"javascript:void(0)\" title=\"点击查看详情\">{{parseParam(item).text}}</a>\r\n\t\t\t\t  \t  </td>\r\n\t\t\t\t      <td>{{item.description}}</td>\r\n\t\t\t\t  \t</tr>\r\n\t\t\t\t  </tbody>\r\n\t\t\t\t</table>\r\n\t        </div>\r\n        </form>\r\n        <button class=\"btn btn-primary\" (click)=\"sendData()\" type=\"submit\">发送数据</button>\r\n        <h5 class=\"card-title text-left\">Response</h5>\r\n        <div class=\"objDefines\" #objResult>\r\n\t\t  \r\n\t\t</div>\r\n    </div>\r\n\r\n     <!-- 显示对象定义详情 -->\r\n\t<div [ngClass]=\"{'show':popWindowId == 1}\" class=\"modal\" tabindex=\"-1\" role=\"dialog\">\r\n\t  <div class=\"modal-dialog\" role=\"document\">\r\n\t    <div class=\"modal-content\">\r\n\t      <div class=\"modal-header\">\r\n\t        <h5 class=\"modal-title\">对象定义详情</h5>\r\n\t        <button  (click)=\"close()\"  type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n\t          <span aria-hidden=\"true\">&times;</span>\r\n\t        </button>\r\n\t      </div>\r\n\t      <div class=\"modal-body objDefines\">\r\n\t        <div>{{objDefines[\"description\"]}}</div>\r\n\t        <div #objDefined></div>\r\n\t      </div>\r\n\t      <div class=\"modal-footer\">\r\n\t        <button (click)=\"close()\" type=\"button\" class=\"btn btn-primary\">关闭</button>\r\n\t      </div>\r\n\t    </div>\r\n\t  </div>\r\n\t</div>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/main/main.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MainComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_json_formatter_js__ = __webpack_require__("./node_modules/json-formatter-js/dist/json-formatter.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_json_formatter_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_json_formatter_js__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__common_myHttpService__ = __webpack_require__("./src/app/common/myHttpService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var MainComponent = /** @class */ (function () {
    function MainComponent(myHttpService) {
        this.myHttpService = myHttpService;
        this.api = {};
        this.tagId = 0;
        this.popWindowId = 0;
        this.objDefines = { properties: null };
        this.formData = {};
    }
    //解析定义类的名称
    MainComponent.prototype.getDefinName = function (str) {
        if (null == str)
            return "";
        return str.replace(/^#.*\//, "");
    };
    //切换标签页
    MainComponent.prototype.showTag = function (tagId) {
        this.tagId = tagId;
    };
    MainComponent.prototype.ngOnInit = function () {
    };
    //显示API详情
    MainComponent.prototype.showApiDescription = function (apiInfo) {
        this.api = apiInfo.api || {};
        this.voObjs = apiInfo.voObjs;
        this.formData = {};
        console.log(apiInfo);
    };
    //参数列表中，如果参数为对象点击查看参数对象详情
    MainComponent.prototype.showObj = function (objName) {
        if (!objName || !objName.voObjName)
            return;
        objName = objName.voObjName;
        this.popWindowId = 1;
        this.objDefines = this.voObjs[this.getDefinName(objName)];
        var el = this.objDefinedContainer.nativeElement;
        el.innerHTML = "";
        var formatter = new __WEBPACK_IMPORTED_MODULE_1_json_formatter_js___default.a(this.objDefines.properties);
        el.appendChild(formatter.render());
    };
    MainComponent.prototype.parseParam = function (obj) {
        var res = {
            type: 1,
            text: "",
            voObjName: null
        };
        //处理普通数组参数,基本类型
        if ('array'.toLocaleLowerCase() == obj.type && obj.items) {
            if (obj.items.type) {
                res.text = obj.type + "<" + obj.items.type + ">";
            }
            else if (obj.items && obj.items.$ref) {
                obj.schema = {
                    $ref: obj.items.$ref
                };
                res.type = 2;
                res.voObjName = obj.schema.$ref;
                res.text = obj.type + "<" + this.getDefinName(obj.schema.$ref) + ">";
            }
        }
        else if (obj.schema) {
            var $ref = null;
            if (obj.schema && 'array' == obj.schema.type) {
                $ref = obj.schema.items.$ref;
                res.text = "array<" + this.getDefinName($ref) + ">";
            }
            else {
                $ref = obj.schema.$ref;
                res.text = this.getDefinName($ref);
            }
            res.type = 2;
            res.voObjName = $ref;
        }
        else {
            res.text = obj.type;
        }
        return res;
    };
    //debug时显示的文本框类型
    MainComponent.prototype.getShowType = function (item) {
        if (item.in == "body") {
            return 'textarea';
        }
        else {
            return 'text';
        }
    };
    MainComponent.prototype.close = function () {
        this.popWindowId = 0;
    };
    //发送数据到后台接口
    MainComponent.prototype.sendData = function () {
        var _this = this;
        var url = this.api.path;
        var params = [];
        var headers = [];
        var flag = true;
        var requiredMsg = "";
        var jsonError = false;
        this.showResult(null);
        if (this.api && this.api.params) {
            this.api.params.forEach(function (item) {
                if (item.required && !_this.formData[item.name]) {
                    requiredMsg += "[" + item.name + "]字段必填\r\n";
                    flag = false;
                    return;
                }
                if (item.in == 'body' && item.schema) {
                    params = _this.strToJSON(_this.formData[item.name]);
                    if (Object.prototype.toString.call(params) != '[object Object]')
                        jsonError = true;
                }
                else if (item.in == 'path') {
                    var reg = new RegExp("{" + item.name + "}");
                    url = url.replace(reg, _this.formData[item.name]);
                }
                else if (item.in == 'header') {
                    headers.push({
                        key: item.name,
                        value: _this.formData[item.name]
                    });
                }
                else {
                    params.push({
                        key: item.name,
                        value: _this.formData[item.name]
                    });
                    headers.push({
                        key: "Content-Type",
                        value: 'application/x-www-form-urlencoded'
                    });
                }
            });
        }
        if (Object.is(jsonError, true)) {
            alert("json格式错误");
            return;
        }
        if (this.api.method && flag == true) {
            this.myHttpService.sendData(this.api.method, url, headers, params).then(function (res) {
                _this.showResult(res);
            });
        }
        if (Object.is(flag, false)) {
            alert(requiredMsg);
        }
    };
    MainComponent.prototype.showResult = function (obj) {
        var el = this.objResultContainer.nativeElement;
        el.innerHTML = "";
        if (!obj)
            return;
        var formatter = new __WEBPACK_IMPORTED_MODULE_1_json_formatter_js___default.a(obj);
        el.appendChild(formatter.render());
    };
    MainComponent.prototype.strToJSON = function (str) {
        var res;
        try {
            res = (new Function("return " + str))();
        }
        catch (e) {
            console.log("JSON格式错误");
            res = null;
        }
        return res;
    };
    ;
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* ViewChild */])('objDefined'),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_0__angular_core__["r" /* ElementRef */])
    ], MainComponent.prototype, "objDefinedContainer", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* ViewChild */])('objResult'),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_0__angular_core__["r" /* ElementRef */])
    ], MainComponent.prototype, "objResultContainer", void 0);
    MainComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'main-component',
            template: __webpack_require__("./src/app/main/main.component.html")
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__common_myHttpService__["a" /* MyHttpService */]])
    ], MainComponent);
    return MainComponent;
}());



/***/ }),

/***/ "./src/app/sider/sider.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"sidebar-sticky\">\r\n    <form >\r\n        <div class=\"form-group\">\r\n            <label for=\"exampleInput\"></label>\r\n            <input type=\"text\" id=\"searchKey\" class=\"form-control\" name=\"searchKey\" [(ngModel)]=\"searchKey\" placeholder=\"Search...\">\r\n        </div>\r\n    </form>\r\n\r\n     <div class=\"api-group\">\r\n        <div class=\"bd-toc-item\" [ngClass]=\"{'active':isSelected(index)}\" (click)=\"selectIndex(index)\" *ngFor=\"let item of apis | apiFilter:searchKey;let index = index\">\r\n            <span class=\"bd-toc-link\" >{{item.name}}</span>\r\n            <ul class=\"nav bd-sidenav\">\r\n                <li (click)=\"showApi(tmp)\" *ngFor=\"let tmp of item.childs\"><span>{{tmp.summary}}</span></li>\r\n            </ul>\r\n        </div>\r\n        <!-- \r\n        <div class=\"bd-toc-item active\">\r\n            <span class=\"bd-toc-link\" >角色管理</span>\r\n            <ul class=\"nav bd-sidenav\">\r\n                <li class=\"active\"><span>新增角色</span></li>\r\n                <li><span>编辑角色</span></li>\r\n                <li><span>删除角色</span></li>\r\n            </ul>\r\n        </div>\r\n         -->\r\n\r\n   </div>\r\n\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/sider/sider.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SiderComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__common_apiService__ = __webpack_require__("./src/app/common/apiService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var SiderComponent = /** @class */ (function () {
    function SiderComponent(apiDocService) {
        this.apiDocService = apiDocService;
        this.showApiEvent = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["t" /* EventEmitter */]();
        this.apis = []; //侧边栏数据
        this.selected = { index: 0 };
        this.searchKey = '';
        this.apiInfo = {}; //API详情shuju
    }
    //在main.component中显示API详情
    SiderComponent.prototype.showApi = function (api) {
        this.apiInfo = this.apiInfo;
        this.apiInfo.api = api;
        this.showApiEvent.emit(this.apiInfo);
    };
    //判断当前选中是否为选中项目
    SiderComponent.prototype.isSelected = function (index) {
        return index == this.selected.index || this.searchKey;
    };
    //设置选中项为当前选中项目
    SiderComponent.prototype.selectIndex = function (index) {
        this.selected.index = index;
        console.log(this.selected);
    };
    SiderComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.apiDocService.getApis().then(function (res) {
            _this.apis = _this.apiDocService.parse(res);
            _this.apiInfo = {
                apis: _this.apis,
                voObjs: res.definitions
            };
        });
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["K" /* Output */])(),
        __metadata("design:type", Object)
    ], SiderComponent.prototype, "showApiEvent", void 0);
    SiderComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'sider-component',
            template: __webpack_require__("./src/app/sider/sider.component.html")
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__common_apiService__["a" /* ApiDocService */]])
    ], SiderComponent);
    return SiderComponent;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};


/***/ }),

/***/ "./src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("./node_modules/@angular/core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("./node_modules/@angular/platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("./src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("./src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_8" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("./src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map
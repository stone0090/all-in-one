(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{"BGR+":function(M,g,e){"use strict";function P(m,l){for(var O=Object.assign({},m),v=0;v<l.length;v+=1){var h=l[v];delete O[h]}return O}g.a=P},ObQG:function(M,g,e){M.exports={container:"container___1sYa-",lang:"lang___l6cji",content:"content___2zk1-",top:"top___1C1Zi",header:"header___5xZ3f",logo:"logo___2hXsy",title:"title___1-xuF",desc:"desc___-njyT",main:"main___x4OjT",icon:"icon___rzGKO",other:"other___lLyaU",register:"register___11Twg",prefixIcon:"prefixIcon___23Xrx"}},PdsH:function(M,g,e){"use strict";e.r(g);var P=e("miYZ"),m=e("tsqr"),l=e("qLMh"),O=e("k1fw"),v=e("9og8"),h=e("tJVT"),H=e("fOrg"),w=e("+KLJ"),C=e("cJ7L"),o=e("VTBJ"),F=e("q1tI"),V=e.n(F),k={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M832 464h-68V240c0-70.7-57.3-128-128-128H388c-70.7 0-128 57.3-128 128v224h-68c-17.7 0-32 14.3-32 32v384c0 17.7 14.3 32 32 32h640c17.7 0 32-14.3 32-32V496c0-17.7-14.3-32-32-32zM332 240c0-30.9 25.1-56 56-56h248c30.9 0 56 25.1 56 56v224H332V240zm460 600H232V536h560v304zM484 701v53c0 4.4 3.6 8 8 8h40c4.4 0 8-3.6 8-8v-53a48.01 48.01 0 10-56 0z"}}]},name:"lock",theme:"outlined"},K=k,W=e("6VBw"),E=function(t,s){return F.createElement(W.a,Object(o.a)(Object(o.a)({},t),{},{ref:s,icon:K}))};E.displayName="LockOutlined";var $=F.forwardRef(E),D=e("VMEa"),I=e("Ff2n"),a=e("nKUr"),T=e("adzw"),X=["fieldProps","proFieldProps"],Z=["fieldProps","proFieldProps"],N="text",Q=function(t){var s=t.fieldProps,i=t.proFieldProps,f=Object(I.a)(t,X);return Object(a.jsx)(T.a,Object(o.a)({valueType:N,fieldProps:s,filedConfig:{valueType:N},proFieldProps:i},f))},Y=function(t){var s=t.fieldProps,i=t.proFieldProps,f=Object(I.a)(t,Z);return Object(a.jsx)(T.a,Object(o.a)({valueType:"password",fieldProps:s,proFieldProps:i,filedConfig:{valueType:N}},f))},z=Q;z.Password=Y,z.displayName="ProFormComponent";var U=z,ge=e("sRBo"),q=e("kaz8"),G=e("uX+g"),_=e("WFLz"),ee=["options","fieldProps","proFieldProps","valueEnum"],ae=V.a.forwardRef(function(n,t){var s=n.options,i=n.fieldProps,f=n.proFieldProps,S=n.valueEnum,b=Object(I.a)(n,ee);return Object(a.jsx)(T.a,Object(o.a)({ref:t,valueType:"checkbox",valueEnum:Object(G.a)(S,void 0),fieldProps:Object(o.a)({options:s},i),lightProps:Object(o.a)({labelFormatter:function(){return Object(a.jsx)(T.a,Object(o.a)({ref:t,valueType:"checkbox",mode:"read",valueEnum:Object(G.a)(S,void 0),filedConfig:{customLightMode:!0},fieldProps:Object(o.a)({options:s},i),proFieldProps:f},b))}},b.lightProps),proFieldProps:f},b))}),te=V.a.forwardRef(function(n,t){var s=n.fieldProps,i=n.children;return Object(a.jsx)(q.a,Object(o.a)(Object(o.a)({ref:t},s),{},{children:i}))}),re=Object(_.a)(te,{valuePropName:"checked"}),J=re;J.Group=ae;var se=J,c=e("9kvl"),ne=e("CwrG"),oe=e("Dp36"),ie=e("ObQG"),u=e.n(ie),le=function(t){var s=t.content;return Object(a.jsx)(w.a,{style:{marginBottom:24},message:s,type:"error",showIcon:!0})},ce=function(){!c.c||setTimeout(function(){var t=c.c.location.query,s=t,i=s.redirect;c.c.push(i||"/")},10)},ue=function(){var t=Object(F.useState)(!1),s=Object(h.a)(t,2),i=s[0],f=s[1],S=Object(F.useState)({}),b=Object(h.a)(S,2),R=b[0],de=b[1],A=Object(c.i)("@@initialState"),y=A.initialState,ve=A.setInitialState,B=Object(c.h)(),fe=function(){var x=Object(v.a)(Object(l.a)().mark(function p(){var d,j;return Object(l.a)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.next=2,y==null||(d=y.fetchUserInfo)===null||d===void 0?void 0:d.call(y);case 2:j=r.sent,j&&ve(Object(O.a)(Object(O.a)({},y),{},{currentUser:j}));case 4:case"end":return r.stop()}},p)}));return function(){return x.apply(this,arguments)}}(),pe=function(){var x=Object(v.a)(Object(l.a)().mark(function p(d){var j;return Object(l.a)().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return f(!0),r.prev=1,r.next=4,Object(oe.b)("/aio/shiro/login",d);case 4:if(j=r.sent,j.success!==!0){r.next=11;break}return m.default.success("\u767B\u5F55\u6210\u529F\uFF01"),r.next=9,fe();case 9:return ce(),r.abrupt("return");case 11:de(j),r.next=17;break;case 14:r.prev=14,r.t0=r.catch(1),m.default.error("\u767B\u5F55\u5931\u8D25\uFF0C\u8BF7\u91CD\u8BD5\uFF01");case 17:f(!1);case 18:case"end":return r.stop()}},p,null,[[1,14]])}));return function(d){return x.apply(this,arguments)}}(),je=R.success;return Object(a.jsxs)("div",{className:u.a.container,children:[Object(a.jsx)("div",{className:u.a.lang,"data-lang":!0,children:c.b&&Object(a.jsx)(c.b,{})}),Object(a.jsxs)("div",{className:u.a.content,children:[Object(a.jsxs)("div",{className:u.a.top,children:[Object(a.jsxs)("div",{className:u.a.header,children:[Object(a.jsx)("img",{alt:"logo",className:u.a.logo,src:"/github.svg"}),Object(a.jsx)("span",{className:u.a.title,children:"All In One"})]}),Object(a.jsx)("div",{className:u.a.desc,children:"https://github.com/stone0090/all-in-one"})]}),Object(a.jsx)("div",{className:u.a.main,children:Object(a.jsxs)(D.a,{initialValues:{autoLogin:!0},submitter:{searchConfig:{submitText:B.formatMessage({id:"pages.login.submit",defaultMessage:"\u767B\u5F55"})},render:function(p,d){return d.pop()},submitButtonProps:{loading:i,size:"large",style:{width:"100%"}}},onFinish:function(){var x=Object(v.a)(Object(l.a)().mark(function p(d){return Object(l.a)().wrap(function(L){for(;;)switch(L.prev=L.next){case 0:pe(d);case 1:case"end":return L.stop()}},p)}));return function(p){return x.apply(this,arguments)}}(),children:[je===!1&&Object(a.jsx)(le,{content:B.formatMessage({id:"pages.login.accountLogin.errorMessage",defaultMessage:"\u8D26\u6237\u6216\u5BC6\u7801\u9519\u8BEF\uFF08admin/ant.design)"})}),Object(a.jsx)(U,{name:"username",fieldProps:{size:"large",prefix:Object(a.jsx)(C.a,{className:u.a.prefixIcon})},placeholder:B.formatMessage({id:"pages.login.username.placeholder",defaultMessage:"\u7528\u6237\u540D: admin or user"}),rules:[{required:!0,message:Object(a.jsx)(c.a,{id:"pages.login.username.required",defaultMessage:"\u8BF7\u8F93\u5165\u7528\u6237\u540D!"})}]}),Object(a.jsx)(U.Password,{name:"password",fieldProps:{size:"large",prefix:Object(a.jsx)($,{className:u.a.prefixIcon})},placeholder:B.formatMessage({id:"pages.login.password.placeholder",defaultMessage:"\u5BC6\u7801: ant.design"}),rules:[{required:!0,message:Object(a.jsx)(c.a,{id:"pages.login.password.required",defaultMessage:"\u8BF7\u8F93\u5165\u5BC6\u7801\uFF01"})}]}),Object(a.jsxs)("div",{style:{marginBottom:24},children:[Object(a.jsx)(se,{noStyle:!0,name:"autoLogin",children:Object(a.jsx)(c.a,{id:"pages.login.rememberMe",defaultMessage:"\u81EA\u52A8\u767B\u5F55"})}),Object(a.jsx)("a",{style:{float:"right"},children:Object(a.jsx)(c.a,{id:"pages.login.forgotPassword",defaultMessage:"\u5FD8\u8BB0\u5BC6\u7801"})})]})]})})]}),Object(a.jsx)(ne.a,{})]})},me=g.default=ue},xvlK:function(M,g,e){"use strict";var P=e("VTBJ"),m=e("q1tI"),l={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"defs",attrs:{},children:[{tag:"style",attrs:{}}]},{tag:"path",attrs:{d:"M482 152h60q8 0 8 8v704q0 8-8 8h-60q-8 0-8-8V160q0-8 8-8z"}},{tag:"path",attrs:{d:"M176 474h672q8 0 8 8v60q0 8-8 8H176q-8 0-8-8v-60q0-8 8-8z"}}]},name:"plus",theme:"outlined"},O=l,v=e("6VBw"),h=function(C,o){return m.createElement(v.a,Object(P.a)(Object(P.a)({},C),{},{ref:o,icon:O}))};h.displayName="PlusOutlined";var H=g.a=m.forwardRef(h)}}]);
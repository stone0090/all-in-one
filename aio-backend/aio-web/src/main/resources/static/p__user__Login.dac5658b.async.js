(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{ObQG:function(v,g,s){v.exports={container:"container___1sYa-",lang:"lang___l6cji",content:"content___2zk1-",top:"top___1C1Zi",header:"header___5xZ3f",logo:"logo___2hXsy",title:"title___1-xuF",desc:"desc___-njyT",main:"main___x4OjT",icon:"icon___rzGKO",other:"other___lLyaU",register:"register___11Twg",prefixIcon:"prefixIcon___23Xrx"}},PdsH:function(v,g,s){"use strict";s.r(g);var Y=s("miYZ"),M=s("tsqr"),b=s("k1fw"),O=s("9og8"),h=s("tJVT"),Z=s("fOrg"),x=s("+KLJ"),B=s("WmNS"),i=s.n(B),T=s("cJ7L"),C=s("FY4R"),j=s("q1tI"),w=s.n(j),A=s("VMEa"),p=s("Qurx"),K=s("tneF"),t=s("9kvl"),R=s("CwrG"),W=s("Dp36"),S=s("ObQG"),_=s.n(S),e=s("nKUr"),X=s.n(e),y=function(d){var l=d.content;return Object(e.jsx)(x.a,{style:{marginBottom:24},message:l,type:"error",showIcon:!0})},F=function(){!t.c||setTimeout(function(){var d=t.c.location.query,l=d,E=l.redirect;t.c.push(E||"/")},10)},N=function(){var d=Object(j.useState)(!1),l=Object(h.a)(d,2),E=l[0],P=l[1],J=Object(j.useState)({}),I=Object(h.a)(J,2),U=I[0],z=I[1],L=Object(t.i)("@@initialState"),c=L.initialState,G=L.setInitialState,f=Object(t.h)(),$=function(){var u=Object(O.a)(i.a.mark(function r(){var n,o;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,c==null||(n=c.fetchUserInfo)===null||n===void 0?void 0:n.call(c);case 2:o=a.sent,o&&G(Object(b.a)(Object(b.a)({},c),{},{currentUser:o}));case 4:case"end":return a.stop()}},r)}));return function(){return u.apply(this,arguments)}}(),Q=function(){var u=Object(O.a)(i.a.mark(function r(n){var o;return i.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return P(!0),a.prev=1,a.next=4,Object(W.b)("/aio/shiro/login",n);case 4:if(o=a.sent,o.success!==!0){a.next=11;break}return M.default.success("\u767B\u5F55\u6210\u529F\uFF01"),a.next=9,$();case 9:return F(),a.abrupt("return");case 11:z(o),a.next=17;break;case 14:a.prev=14,a.t0=a.catch(1),M.default.error("\u767B\u5F55\u5931\u8D25\uFF0C\u8BF7\u91CD\u8BD5\uFF01");case 17:P(!1);case 18:case"end":return a.stop()}},r,null,[[1,14]])}));return function(n){return u.apply(this,arguments)}}(),V=U.success,H=U.data;return Object(e.jsxs)("div",{className:_.a.container,children:[Object(e.jsx)("div",{className:_.a.lang,"data-lang":!0,children:t.b&&Object(e.jsx)(t.b,{})}),Object(e.jsxs)("div",{className:_.a.content,children:[Object(e.jsxs)("div",{className:_.a.top,children:[Object(e.jsxs)("div",{className:_.a.header,children:[Object(e.jsx)("img",{alt:"logo",className:_.a.logo,src:"/github.svg"}),Object(e.jsx)("span",{className:_.a.title,children:"Java Scaffold"})]}),Object(e.jsx)("div",{className:_.a.desc,children:"https://github.com/stone0090/java-scaffold"})]}),Object(e.jsx)("div",{className:_.a.main,children:Object(e.jsxs)(A.b,{initialValues:{autoLogin:!0},submitter:{searchConfig:{submitText:f.formatMessage({id:"pages.login.submit",defaultMessage:"\u767B\u5F55"})},render:function(r,n){return n.pop()},submitButtonProps:{loading:E,size:"large",style:{width:"100%"}}},onFinish:function(){var u=Object(O.a)(i.a.mark(function r(n){return i.a.wrap(function(m){for(;;)switch(m.prev=m.next){case 0:Q(n);case 1:case"end":return m.stop()}},r)}));return function(r){return u.apply(this,arguments)}}(),children:[V===!1&&Object(e.jsx)(y,{content:f.formatMessage({id:"pages.login.accountLogin.errorMessage",defaultMessage:"\u8D26\u6237\u6216\u5BC6\u7801\u9519\u8BEF\uFF08admin/ant.design)"})}),Object(e.jsx)(p.a,{name:"username",fieldProps:{size:"large",prefix:Object(e.jsx)(T.a,{className:_.a.prefixIcon})},placeholder:f.formatMessage({id:"pages.login.username.placeholder",defaultMessage:"\u7528\u6237\u540D: admin or user"}),rules:[{required:!0,message:Object(e.jsx)(t.a,{id:"pages.login.username.required",defaultMessage:"\u8BF7\u8F93\u5165\u7528\u6237\u540D!"})}]}),Object(e.jsx)(p.a.Password,{name:"password",fieldProps:{size:"large",prefix:Object(e.jsx)(C.a,{className:_.a.prefixIcon})},placeholder:f.formatMessage({id:"pages.login.password.placeholder",defaultMessage:"\u5BC6\u7801: ant.design"}),rules:[{required:!0,message:Object(e.jsx)(t.a,{id:"pages.login.password.required",defaultMessage:"\u8BF7\u8F93\u5165\u5BC6\u7801\uFF01"})}]}),Object(e.jsxs)("div",{style:{marginBottom:24},children:[Object(e.jsx)(K.a,{noStyle:!0,name:"autoLogin",children:Object(e.jsx)(t.a,{id:"pages.login.rememberMe",defaultMessage:"\u81EA\u52A8\u767B\u5F55"})}),Object(e.jsx)("a",{style:{float:"right"},children:Object(e.jsx)(t.a,{id:"pages.login.forgotPassword",defaultMessage:"\u5FD8\u8BB0\u5BC6\u7801"})})]})]})})]}),Object(e.jsx)(R.a,{})]})};g.default=N}}]);

(window.webpackJsonp=window.webpackJsonp||[]).push([[3],{Ncoo:function(N,s,d){"use strict";d.r(s),d.d(s,"TagAngleInterpolationBracket",function(){return z}),d.d(s,"TagAngleInterpolationDollar",function(){return y}),d.d(s,"TagAutoInterpolationBracket",function(){return Z}),d.d(s,"TagAutoInterpolationDollar",function(){return L}),d.d(s,"TagBracketInterpolationBracket",function(){return M}),d.d(s,"TagBracketInterpolationDollar",function(){return R});var t=d("jrin"),c=d("k1fw"),C=d("rAM+"),T=d("8z58");/*!-----------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Version: 0.39.0(ff3621a3fa6389873be5412d17554294ea1b0941)
 * Released under the MIT license
 * https://github.com/microsoft/monaco-editor/blob/main/LICENSE.txt
 *-----------------------------------------------------------------------------*/var h=Object.defineProperty,w=Object.getOwnPropertyDescriptor,S=Object.getOwnPropertyNames,P=Object.prototype.hasOwnProperty,E=function(_,r,u,k){if(r&&typeof r=="object"||typeof r=="function"){var o=Object(C.a)(S(r)),a;try{var O=function(){var i=a.value;!P.call(_,i)&&i!==u&&h(_,i,{get:function(){return r[i]},enumerable:!(k=w(r,i))||k.enumerable})};for(o.s();!(a=o.n()).done;)O()}catch(e){o.e(e)}finally{o.f()}}return _},$=function(_,r,u){return E(_,r,"default"),u&&E(u,r,"default")},p={};$(p,T);var b=["assign","flush","ftl","return","global","import","include","break","continue","local","nested","nt","setting","stop","t","lt","rt","fallback"],m=["attempt","autoesc","autoEsc","compress","comment","escape","noescape","function","if","list","items","sep","macro","noparse","noParse","noautoesc","noAutoEsc","outputformat","switch","visit","recurse"],g={close:">",id:"angle",open:"<"},A={close:"\\]",id:"bracket",open:"\\["},I={close:"[>\\]]",id:"auto",open:"[<\\[]"},j={close:"\\}",id:"dollar",open1:"\\$",open2:"\\{"},v={close:"\\]",id:"bracket",open1:"\\[",open2:"="};function f(n){return{brackets:[["<",">"],["[","]"],["(",")"],["{","}"]],comments:{blockComment:["".concat(n.open,"--"),"--".concat(n.close)]},autoCloseBefore:`
\r	 }]),.:;=`,autoClosingPairs:[{open:"{",close:"}"},{open:"[",close:"]"},{open:"(",close:")"},{open:'"',close:'"',notIn:["string"]},{open:"'",close:"'",notIn:["string"]}],surroundingPairs:[{open:'"',close:'"'},{open:"'",close:"'"},{open:"{",close:"}"},{open:"[",close:"]"},{open:"(",close:")"},{open:"<",close:">"}],folding:{markers:{start:new RegExp("".concat(n.open,"#(?:").concat(m.join("|"),")([^/").concat(n.close,"]*(?!/)").concat(n.close,")[^").concat(n.open,"]*$")),end:new RegExp("".concat(n.open,"/#(?:").concat(m.join("|"),")[\\r\\n\\t ]*>"))}},onEnterRules:[{beforeText:new RegExp("".concat(n.open,"#(?!(?:").concat(b.join("|"),"))([a-zA-Z_]+)([^/").concat(n.close,"]*(?!/)").concat(n.close,")[^").concat(n.open,"]*$")),afterText:new RegExp("^".concat(n.open,"/#([a-zA-Z_]+)[\\r\\n\\t ]*").concat(n.close,"$")),action:{indentAction:p.languages.IndentAction.IndentOutdent}},{beforeText:new RegExp("".concat(n.open,"#(?!(?:").concat(b.join("|"),"))([a-zA-Z_]+)([^/").concat(n.close,"]*(?!/)").concat(n.close,")[^").concat(n.open,"]*$")),action:{indentAction:p.languages.IndentAction.Indent}}]}}function x(){return{brackets:[["<",">"],["[","]"],["(",")"],["{","}"]],autoCloseBefore:`
\r	 }]),.:;=`,autoClosingPairs:[{open:"{",close:"}"},{open:"[",close:"]"},{open:"(",close:")"},{open:'"',close:'"',notIn:["string"]},{open:"'",close:"'",notIn:["string"]}],surroundingPairs:[{open:'"',close:'"'},{open:"'",close:"'"},{open:"{",close:"}"},{open:"[",close:"]"},{open:"(",close:")"},{open:"<",close:">"}],folding:{markers:{start:new RegExp("[<\\[]#(?:".concat(m.join("|"),")([^/>\\]]*(?!/)[>\\]])[^<\\[]*$")),end:new RegExp("[<\\[]/#(?:".concat(m.join("|"),")[\\r\\n\\t ]*>"))}},onEnterRules:[{beforeText:new RegExp("[<\\[]#(?!(?:".concat(b.join("|"),"))([a-zA-Z_]+)([^/>\\]]*(?!/)[>\\]])[^[<\\[]]*$")),afterText:new RegExp("^[<\\[]/#([a-zA-Z_]+)[\\r\\n\\t ]*[>\\]]$"),action:{indentAction:p.languages.IndentAction.IndentOutdent}},{beforeText:new RegExp("[<\\[]#(?!(?:".concat(b.join("|"),"))([a-zA-Z_]+)([^/>\\]]*(?!/)[>\\]])[^[<\\[]]*$")),action:{indentAction:p.languages.IndentAction.Indent}}]}}function l(n,_){var r,u,k,o,a,O="_".concat(n.id,"_").concat(_.id),e=function(F){return F.replace(/__id__/g,O)},i=function(F){var K=F.source.replace(/__id__/g,O);return new RegExp(K,F.flags)};return a={unicode:!0,includeLF:!1,start:e("default__id__"),ignoreCase:!1,defaultToken:"invalid",tokenPostfix:".freemarker2",brackets:[{open:"{",close:"}",token:"delimiter.curly"},{open:"[",close:"]",token:"delimiter.square"},{open:"(",close:")",token:"delimiter.parenthesis"},{open:"<",close:">",token:"delimiter.angle"}]},Object(t.a)(a,e("open__id__"),new RegExp(n.open)),Object(t.a)(a,e("close__id__"),new RegExp(n.close)),Object(t.a)(a,e("iOpen1__id__"),new RegExp(_.open1)),Object(t.a)(a,e("iOpen2__id__"),new RegExp(_.open2)),Object(t.a)(a,e("iClose__id__"),new RegExp(_.close)),Object(t.a)(a,e("startTag__id__"),i(/(@open__id__)(#)/)),Object(t.a)(a,e("endTag__id__"),i(/(@open__id__)(\/#)/)),Object(t.a)(a,e("startOrEndTag__id__"),i(/(@open__id__)(\/?#)/)),Object(t.a)(a,e("closeTag1__id__"),i(/((?:@blank)*)(@close__id__)/)),Object(t.a)(a,e("closeTag2__id__"),i(/((?:@blank)*\/?)(@close__id__)/)),Object(t.a)(a,"blank",/[ \t\n\r]/),Object(t.a)(a,"keywords",["false","true","in","as","using"]),Object(t.a)(a,"directiveStartCloseTag1",/attempt|recover|sep|auto[eE]sc|no(?:autoe|AutoE)sc|compress|default|no[eE]scape|comment|no[pP]arse/),Object(t.a)(a,"directiveStartCloseTag2",/else|break|continue|return|stop|flush|t|lt|rt|nt|nested|recurse|fallback|ftl/),Object(t.a)(a,"directiveStartBlank",/if|else[iI]f|list|for[eE]ach|switch|case|assign|global|local|include|import|function|macro|transform|visit|stop|return|call|setting|output[fF]ormat|nested|recurse|escape|ftl|items/),Object(t.a)(a,"directiveEndCloseTag1",/if|list|items|sep|recover|attempt|for[eE]ach|local|global|assign|function|macro|output[fF]ormat|auto[eE]sc|no(?:autoe|AutoE)sc|compress|transform|switch|escape|no[eE]scape/),Object(t.a)(a,"escapedChar",/\\(?:[ntrfbgla\\'"\{=]|(?:x[0-9A-Fa-f]{1,4}))/),Object(t.a)(a,"asciiDigit",/[0-9]/),Object(t.a)(a,"integer",/[0-9]+/),Object(t.a)(a,"nonEscapedIdStartChar",/[\$@-Z_a-z\u00AA\u00B5\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u1FFF\u2071\u207F\u2090-\u209C\u2102\u2107\u210A-\u2113\u2115\u2119-\u211D\u2124\u2126\u2128\u212A-\u212D\u212F-\u2139\u213C-\u213F\u2145-\u2149\u214E\u2183-\u2184\u2C00-\u2C2E\u2C30-\u2C5E\u2C60-\u2CE4\u2CEB-\u2CEE\u2CF2-\u2CF3\u2D00-\u2D25\u2D27\u2D2D\u2D30-\u2D67\u2D6F\u2D80-\u2D96\u2DA0-\u2DA6\u2DA8-\u2DAE\u2DB0-\u2DB6\u2DB8-\u2DBE\u2DC0-\u2DC6\u2DC8-\u2DCE\u2DD0-\u2DD6\u2DD8-\u2DDE\u2E2F\u3005-\u3006\u3031-\u3035\u303B-\u303C\u3040-\u318F\u31A0-\u31BA\u31F0-\u31FF\u3300-\u337F\u3400-\u4DB5\u4E00-\uA48C\uA4D0-\uA4FD\uA500-\uA60C\uA610-\uA62B\uA640-\uA66E\uA67F-\uA697\uA6A0-\uA6E5\uA717-\uA71F\uA722-\uA788\uA78B-\uA78E\uA790-\uA793\uA7A0-\uA7AA\uA7F8-\uA801\uA803-\uA805\uA807-\uA80A\uA80C-\uA822\uA840-\uA873\uA882-\uA8B3\uA8D0-\uA8D9\uA8F2-\uA8F7\uA8FB\uA900-\uA925\uA930-\uA946\uA960-\uA97C\uA984-\uA9B2\uA9CF-\uA9D9\uAA00-\uAA28\uAA40-\uAA42\uAA44-\uAA4B\uAA50-\uAA59\uAA60-\uAA76\uAA7A\uAA80-\uAAAF\uAAB1\uAAB5-\uAAB6\uAAB9-\uAABD\uAAC0\uAAC2\uAADB-\uAADD\uAAE0-\uAAEA\uAAF2-\uAAF4\uAB01-\uAB06\uAB09-\uAB0E\uAB11-\uAB16\uAB20-\uAB26\uAB28-\uAB2E\uABC0-\uABE2\uABF0-\uABF9\uAC00-\uD7A3\uD7B0-\uD7C6\uD7CB-\uD7FB\uF900-\uFB06\uFB13-\uFB17\uFB1D\uFB1F-\uFB28\uFB2A-\uFB36\uFB38-\uFB3C\uFB3E\uFB40-\uFB41\uFB43-\uFB44\uFB46-\uFBB1\uFBD3-\uFD3D\uFD50-\uFD8F\uFD92-\uFDC7\uFDF0-\uFDFB\uFE70-\uFE74\uFE76-\uFEFC\uFF10-\uFF19\uFF21-\uFF3A\uFF41-\uFF5A\uFF66-\uFFBE\uFFC2-\uFFC7\uFFCA-\uFFCF\uFFD2-\uFFD7\uFFDA-\uFFDC]/),Object(t.a)(a,"escapedIdChar",/\\[\-\.:#]/),Object(t.a)(a,"idStartChar",/(?:@nonEscapedIdStartChar)|(?:@escapedIdChar)/),Object(t.a)(a,"id",/(?:@idStartChar)(?:(?:@idStartChar)|(?:@asciiDigit))*/),Object(t.a)(a,"specialHashKeys",/\*\*|\*|false|true|in|as|using/),Object(t.a)(a,"namedSymbols",/&lt;=|&gt;=|\\lte|\\lt|&lt;|\\gte|\\gt|&gt;|&amp;&amp;|\\and|-&gt;|->|==|!=|\+=|-=|\*=|\/=|%=|\+\+|--|<=|&&|\|\||:|\.\.\.|\.\.\*|\.\.<|\.\.!|\?\?|=|<|\+|-|\*|\/|%|\||\.\.|\?|!|&|\.|,|;/),Object(t.a)(a,"arrows",["->","-&gt;"]),Object(t.a)(a,"delimiters",[";",":",",","."]),Object(t.a)(a,"stringOperators",["lte","lt","gte","gt"]),Object(t.a)(a,"noParseTags",["noparse","noParse","comment"]),Object(t.a)(a,"tokenizer",(o={},Object(t.a)(o,e("default__id__"),[{include:e("@directive_token__id__")},{include:e("@interpolation_and_text_token__id__")}]),Object(t.a)(o,e("fmExpression__id__.directive"),[{include:e("@blank_and_expression_comment_token__id__")},{include:e("@directive_end_token__id__")},{include:e("@expression_token__id__")}]),Object(t.a)(o,e("fmExpression__id__.interpolation"),[{include:e("@blank_and_expression_comment_token__id__")},{include:e("@expression_token__id__")},{include:e("@greater_operators_token__id__")}]),Object(t.a)(o,e("inParen__id__.plain"),[{include:e("@blank_and_expression_comment_token__id__")},{include:e("@directive_end_token__id__")},{include:e("@expression_token__id__")}]),Object(t.a)(o,e("inParen__id__.gt"),[{include:e("@blank_and_expression_comment_token__id__")},{include:e("@expression_token__id__")},{include:e("@greater_operators_token__id__")}]),Object(t.a)(o,e("noSpaceExpression__id__"),[{include:e("@no_space_expression_end_token__id__")},{include:e("@directive_end_token__id__")},{include:e("@expression_token__id__")}]),Object(t.a)(o,e("unifiedCall__id__"),[{include:e("@unified_call_token__id__")}]),Object(t.a)(o,e("singleString__id__"),[{include:e("@string_single_token__id__")}]),Object(t.a)(o,e("doubleString__id__"),[{include:e("@string_double_token__id__")}]),Object(t.a)(o,e("rawSingleString__id__"),[{include:e("@string_single_raw_token__id__")}]),Object(t.a)(o,e("rawDoubleString__id__"),[{include:e("@string_double_raw_token__id__")}]),Object(t.a)(o,e("expressionComment__id__"),[{include:e("@expression_comment_token__id__")}]),Object(t.a)(o,e("noParse__id__"),[{include:e("@no_parse_token__id__")}]),Object(t.a)(o,e("terseComment__id__"),[{include:e("@terse_comment_token__id__")}]),Object(t.a)(o,e("directive_token__id__"),[[i(/(?:@startTag__id__)(@directiveStartCloseTag1)(?:@closeTag1__id__)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive"},{cases:{"@noParseTags":{token:"tag",next:e("@noParse__id__.$3")},"@default":{token:"tag"}}},{token:"delimiter.directive"},{token:"@brackets.directive"}]],[i(/(?:@startTag__id__)(@directiveStartCloseTag2)(?:@closeTag2__id__)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag"},{token:"delimiter.directive"},{token:"@brackets.directive"}]],[i(/(?:@startTag__id__)(@directiveStartBlank)(@blank)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag"},{token:"",next:e("@fmExpression__id__.directive")}]],[i(/(?:@endTag__id__)(@directiveEndCloseTag1)(?:@closeTag1__id__)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag"},{token:"delimiter.directive"},{token:"@brackets.directive"}]],[i(/(@open__id__)(@)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive",next:e("@unifiedCall__id__")}]],[i(/(@open__id__)(\/@)((?:(?:@id)(?:\.(?:@id))*)?)(?:@closeTag1__id__)/),[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag"},{token:"delimiter.directive"},{token:"@brackets.directive"}]],[i(/(@open__id__)#--/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:{token:"comment",next:e("@terseComment__id__")}],[i(/(?:@startOrEndTag__id__)([a-zA-Z_]+)/),n.id==="auto"?{cases:{"$1==<":{token:"@rematch",switchTo:"@default_angle_".concat(_.id)},"$1==[":{token:"@rematch",switchTo:"@default_bracket_".concat(_.id)}}}:[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag.invalid",next:e("@fmExpression__id__.directive")}]]]),Object(t.a)(o,e("interpolation_and_text_token__id__"),[[i(/(@iOpen1__id__)(@iOpen2__id__)/),[{token:_.id==="bracket"?"@brackets.interpolation":"delimiter.interpolation"},{token:_.id==="bracket"?"delimiter.interpolation":"@brackets.interpolation",next:e("@fmExpression__id__.interpolation")}]],[/[\$#<\[\{]|(?:@blank)+|[^\$<#\[\{\n\r\t ]+/,{token:"source"}]]),Object(t.a)(o,e("string_single_token__id__"),[[/[^'\\]/,{token:"string"}],[/@escapedChar/,{token:"string.escape"}],[/'/,{token:"string",next:"@pop"}]]),Object(t.a)(o,e("string_double_token__id__"),[[/[^"\\]/,{token:"string"}],[/@escapedChar/,{token:"string.escape"}],[/"/,{token:"string",next:"@pop"}]]),Object(t.a)(o,e("string_single_raw_token__id__"),[[/[^']+/,{token:"string.raw"}],[/'/,{token:"string.raw",next:"@pop"}]]),Object(t.a)(o,e("string_double_raw_token__id__"),[[/[^"]+/,{token:"string.raw"}],[/"/,{token:"string.raw",next:"@pop"}]]),Object(t.a)(o,e("expression_token__id__"),[[/(r?)(['"])/,{cases:{"r'":[{token:"keyword"},{token:"string.raw",next:e("@rawSingleString__id__")}],'r"':[{token:"keyword"},{token:"string.raw",next:e("@rawDoubleString__id__")}],"'":[{token:"source"},{token:"string",next:e("@singleString__id__")}],'"':[{token:"source"},{token:"string",next:e("@doubleString__id__")}]}}],[/(?:@integer)(?:\.(?:@integer))?/,{cases:{"(?:@integer)":{token:"number"},"@default":{token:"number.float"}}}],[/(\.)(@blank*)(@specialHashKeys)/,[{token:"delimiter"},{token:""},{token:"identifier"}]],[/(?:@namedSymbols)/,{cases:{"@arrows":{token:"meta.arrow"},"@delimiters":{token:"delimiter"},"@default":{token:"operators"}}}],[/@id/,{cases:{"@keywords":{token:"keyword.$0"},"@stringOperators":{token:"operators"},"@default":{token:"identifier"}}}],[/[\[\]\(\)\{\}]/,{cases:{"\\[":{cases:{"$S2==gt":{token:"@brackets",next:e("@inParen__id__.gt")},"@default":{token:"@brackets",next:e("@inParen__id__.plain")}}},"\\]":{cases:Object(c.a)(Object(c.a)(Object(c.a)({},_.id==="bracket"?{"$S2==interpolation":{token:"@brackets.interpolation",next:"@popall"}}:{}),n.id==="bracket"?{"$S2==directive":{token:"@brackets.directive",next:"@popall"}}:{}),{},(r={},Object(t.a)(r,e("$S1==inParen__id__"),{token:"@brackets",next:"@pop"}),Object(t.a)(r,"@default",{token:"@brackets"}),r))},"\\(":{token:"@brackets",next:e("@inParen__id__.gt")},"\\)":{cases:(u={},Object(t.a)(u,e("$S1==inParen__id__"),{token:"@brackets",next:"@pop"}),Object(t.a)(u,"@default",{token:"@brackets"}),u)},"\\{":{cases:{"$S2==gt":{token:"@brackets",next:e("@inParen__id__.gt")},"@default":{token:"@brackets",next:e("@inParen__id__.plain")}}},"\\}":{cases:Object(c.a)(Object(c.a)({},_.id==="bracket"?{}:{"$S2==interpolation":{token:"@brackets.interpolation",next:"@popall"}}),{},(k={},Object(t.a)(k,e("$S1==inParen__id__"),{token:"@brackets",next:"@pop"}),Object(t.a)(k,"@default",{token:"@brackets"}),k))}}}],[/\$\{/,{token:"delimiter.invalid"}]]),Object(t.a)(o,e("blank_and_expression_comment_token__id__"),[[/(?:@blank)+/,{token:""}],[/[<\[][#!]--/,{token:"comment",next:e("@expressionComment__id__")}]]),Object(t.a)(o,e("directive_end_token__id__"),[[/>/,n.id==="bracket"?{token:"operators"}:{token:"@brackets.directive",next:"@popall"}],[i(/(\/)(@close__id__)/),[{token:"delimiter.directive"},{token:"@brackets.directive",next:"@popall"}]]]),Object(t.a)(o,e("greater_operators_token__id__"),[[/>/,{token:"operators"}],[/>=/,{token:"operators"}]]),Object(t.a)(o,e("no_space_expression_end_token__id__"),[[/(?:@blank)+/,{token:"",switchTo:e("@fmExpression__id__.directive")}]]),Object(t.a)(o,e("unified_call_token__id__"),[[/(@id)((?:@blank)+)/,[{token:"tag"},{token:"",next:e("@fmExpression__id__.directive")}]],[i(/(@id)(\/?)(@close__id__)/),[{token:"tag"},{token:"delimiter.directive"},{token:"@brackets.directive",next:"@popall"}]],[/./,{token:"@rematch",next:e("@noSpaceExpression__id__")}]]),Object(t.a)(o,e("no_parse_token__id__"),[[i(/(@open__id__)(\/#?)([a-zA-Z]+)((?:@blank)*)(@close__id__)/),{cases:{"$S2==$3":[{token:"@brackets.directive"},{token:"delimiter.directive"},{token:"tag"},{token:""},{token:"@brackets.directive",next:"@popall"}],"$S2==comment":[{token:"comment"},{token:"comment"},{token:"comment"},{token:"comment"},{token:"comment"}],"@default":[{token:"source"},{token:"source"},{token:"source"},{token:"source"},{token:"source"}]}}],[/[^<\[\-]+|[<\[\-]/,{cases:{"$S2==comment":{token:"comment"},"@default":{token:"source"}}}]]),Object(t.a)(o,e("expression_comment_token__id__"),[[/--[>\]]/,{token:"comment",next:"@pop"}],[/[^\->\]]+|[>\]\-]/,{token:"comment"}]]),Object(t.a)(o,e("terse_comment_token__id__"),[[i(/--(?:@close__id__)/),{token:"comment",next:"@popall"}],[/[^<\[\-]+|[<\[\-]/,{token:"comment"}]]),o)),a}function D(n){var _=l(g,n),r=l(A,n),u=l(I,n);return Object(c.a)(Object(c.a)(Object(c.a)(Object(c.a)({},_),r),u),{},{unicode:!0,includeLF:!1,start:"default_auto_".concat(n.id),ignoreCase:!1,defaultToken:"invalid",tokenPostfix:".freemarker2",brackets:[{open:"{",close:"}",token:"delimiter.curly"},{open:"[",close:"]",token:"delimiter.square"},{open:"(",close:")",token:"delimiter.parenthesis"},{open:"<",close:">",token:"delimiter.angle"}],tokenizer:Object(c.a)(Object(c.a)(Object(c.a)({},_.tokenizer),r.tokenizer),u.tokenizer)})}var y={conf:f(g),language:l(g,j)},R={conf:f(A),language:l(A,j)},z={conf:f(g),language:l(g,v)},M={conf:f(A),language:l(A,v)},L={conf:x(),language:D(j)},Z={conf:x(),language:D(v)}}}]);
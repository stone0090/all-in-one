import React, {useRef, useEffect} from 'react';
import * as monaco from "monaco-editor";

// @ts-ignore
self.MonacoEnvironment = {
  getWorkerUrl: function (_moduleId: any, label: string) {
    if (label === 'json') {
      return './json.worker.bundle.js';
    }
    if (label === 'css' || label === 'scss' || label === 'less') {
      return './css.worker.bundle.js';
    }
    if (label === 'html' || label === 'handlebars' || label === 'razor') {
      return './html.worker.bundle.js';
    }
    if (label === 'typescript' || label === 'javascript') {
      return './ts.worker.bundle.js';
    }
    return './editor.worker.bundle.js';
  }
};

export type CodeEditorProps = {
  onChange?: (value?: string) => void;
  id?: string;
  language?: string;
  value?: string;
  height?: number;
  isReadOnly?: boolean;
  formVisible?: boolean;
};

const CodeEditor: React.FC<CodeEditorProps> = (props) => {

  const {
    onChange,
    language,
    value,
    height,
    isReadOnly,
    formVisible,
  } = props;
  const divRef = useRef<HTMLDivElement>(null);
  const editorRef = useRef<monaco.editor.IStandaloneCodeEditor>();

  useEffect(() => {
    if (divRef.current && formVisible) {
      editorRef.current = monaco.editor.create(divRef.current, {
        language: language,
        value: value,
        readOnly: isReadOnly || false,
        lineNumbers: "on", // 行号 取值： "on" | "off" | "relative" | "interval" | function
        roundedSelection: false,
        scrollBeyondLastLine: false,
        theme: "vs-dark", // 编辑器主题颜色
        automaticLayout: true,
        // folding: true, // 是否折叠
        // foldingHighlight: true, // 折叠等高线
        // foldingStrategy: 'indentation', // 折叠方式  auto | indentation
        // showFoldingControls: 'always', // 是否一直显示折叠 always | mouseover
        // disableLayerHinting: true, // 等宽优化
        // emptySelectionClipboard: false, // 空选择剪切板
        // selectionClipboard: false, // 选择剪切板
        // codeLens: false, // 代码镜头
        // scrollBeyondLastLine: false, // 滚动完最后一行后再滚动一屏幕
        // colorDecorators: true, // 颜色装饰器
        // accessibilitySupport: 'off', // 辅助功能支持  "auto" | "off" | "on"
        // lineNumbersMinChars: 5, // 行号最小字符 number
      });
      editorRef.current.onDidBlurEditorText(() => {
        if (onChange && editorRef.current) {
          onChange(editorRef.current.getValue());
        }
      });
    }
    return () => {
      if (editorRef.current) {
        editorRef.current.dispose();
        editorRef.current = undefined;
      }
    }
  }, [divRef, editorRef, language, value, isReadOnly, formVisible]);

  return <div style={{width: '100%', height: height || 200 + 'px'}} ref={divRef}/>;
}

export default CodeEditor;

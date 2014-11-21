/*
Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	 config.font_names = '宋体;楷体;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
	 config.toolbar = 'Basic';
	 config.toolbar_Basic =
		    [
		        { name: 'styles', items: ['Font', 'FontSize'] }, //样式栏：字体、大小
		       
		        {name: 'colors', items: ['TextColor', 'BGColor'] }, //颜色栏：文本颜色、背景颜色
		       
		        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat'] }, //基本样式栏：加粗、倾斜、下划线、删除线、下标、上标、移除样式
		        '/',
		        {name: 'paragraph', items: ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] }, //对齐栏：左对齐、中心对齐、右对齐、两端对齐
		      
		        {name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule'] }, //插入栏：图像、flash、表格、水平线
		        {name: 'links', items: ['Link', 'Unlink'] }, //超链接栏：增加超链接、取消超链接
		        {name: 'clipboard', items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ,'-','Preview','NewPage']},//撤销,重做，预览,新建
		        {name: 'document', items: ['Source']}//源代码栏：查看源代码
		    ];
};

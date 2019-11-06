/**
 * 上传到服务器，需要改变getRoot函数
 * 
 * **/
function getRoot() {
	var host = window.location.host;
	var href = window.location.href;
	var i = host.length;
	
	//本地
	//var j = href.indexOf("/", i + 8);
	//上传到服务器
	var j = href.indexOf("/", i);
	//alert(href.substring(0, j));
	return href.substring(0, j);
}


UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
UE.Editor.prototype.getActionUrl = function(action) {  
	var ROOT = getRoot();
	//alert(ROOT);
	//alert(action);
	if(action == 'uploadimage' || action == 'uploadscrawl'
		 || action == 'uploadsnap' || action == 'catchimage') {
		return ROOT+"/jsp/files/ueditorimgupload.action";
	}else if (action == 'uploadvideo') {
		return ROOT+"/jsp/files/ueditorvideoupload.action";
	}else if (action == 'uploadfile'){
		return ROOT+"/jsp/files/ueditorfileupload.action";
	}else if(action=='listimage'){
		return ROOT+"/jsp/files/listimageupload.action";
	}else if(action=='listfile'){
		return ROOT+"/jsp/files/listfileupload.action";
    }else {  
        return this._bkGetActionUrl.call(this, action);  
    }  
}
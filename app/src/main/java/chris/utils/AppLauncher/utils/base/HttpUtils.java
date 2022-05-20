package chris.utils.AppLauncher.utils.base;


public class HttpUtils {

	public static String getContentTypeByFile(String fileName) {
		if (fileName == null || fileName.trim().length() <= 0) {
			return "application/octet-stream";
		}
		String contentType = null;
		fileName = fileName.trim().toLowerCase();
		if (fileName.endsWith(".tif")) {
			contentType = "image/tiff";
		} else if (fileName.endsWith(".001")) {
			contentType = "application/x-001";
		} else if (fileName.endsWith(".301")) {
			contentType = "application/x-301";
		} else if (fileName.endsWith(".323")) {
			contentType = "text/h323";
		} else if (fileName.endsWith(".906")) {
			contentType = "application/x-906";
		} else if (fileName.endsWith(".907")) {
			contentType = "drawing/907";
		} else if (fileName.endsWith(".a11")) {
			contentType = "application/x-a11";
		} else if (fileName.endsWith(".acp")) {
			contentType = "audio/x-mei-aac";
		} else if (fileName.endsWith(".ai")) {
			contentType = "application/postscript";
		} else if (fileName.endsWith(".aif")) {
			contentType = "audio/aiff";
		} else if (fileName.endsWith(".aifc")) {
			contentType = "audio/aiff";
		} else if (fileName.endsWith(".aiff")) {
			contentType = "audio/aiff";
		} else if (fileName.endsWith(".anv")) {
			contentType = "application/x-anv";
		} else if (fileName.endsWith(".asa")) {
			contentType = "text/asa";
		} else if (fileName.endsWith(".asf")) {
			contentType = "video/x-ms-asf";
		} else if (fileName.endsWith(".asp")) {
			contentType = "text/asp";
		} else if (fileName.endsWith(".asx")) {
			contentType = "video/x-ms-asf";
		} else if (fileName.endsWith(".au")) {
			contentType = "audio/basic";
		} else if (fileName.endsWith(".avi")) {
			contentType = "video/avi";
		} else if (fileName.endsWith(".awf")) {
			contentType = "application/vnd.adobe.workflow";
		} else if (fileName.endsWith(".biz")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".bmp")) {
			contentType = "application/x-bmp";
		} else if (fileName.endsWith(".bot")) {
			contentType = "application/x-bot";
		} else if (fileName.endsWith(".c4t")) {
			contentType = "application/x-c4t";
		} else if (fileName.endsWith(".c90")) {
			contentType = "application/x-c90";
		} else if (fileName.endsWith(".cal")) {
			contentType = "application/x-cals";
		} else if (fileName.endsWith(".cat")) {
			contentType = "application/vnd.ms-pki.seccat";
		} else if (fileName.endsWith(".cdf")) {
			contentType = "application/x-netcdf";
		} else if (fileName.endsWith(".cdr")) {
			contentType = "application/x-cdr";
		} else if (fileName.endsWith(".cel")) {
			contentType = "application/x-cel";
		} else if (fileName.endsWith(".cer")) {
			contentType = "application/x-x509-ca-cert";
		} else if (fileName.endsWith(".cg4")) {
			contentType = "application/x-g4";
		} else if (fileName.endsWith(".cgm")) {
			contentType = "application/x-cgm";
		} else if (fileName.endsWith(".cit")) {
			contentType = "application/x-cit";
		} else if (fileName.endsWith(".class")) {
			contentType = "java/*";
		} else if (fileName.endsWith(".cml")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".cmp")) {
			contentType = "application/x-cmp";
		} else if (fileName.endsWith(".cmx")) {
			contentType = "application/x-cmx";
		} else if (fileName.endsWith(".cot")) {
			contentType = "application/x-cot";
		} else if (fileName.endsWith(".crl")) {
			contentType = "application/pkix-crl";
		} else if (fileName.endsWith(".crt")) {
			contentType = "application/x-x509-ca-cert";
		} else if (fileName.endsWith(".csi")) {
			contentType = "application/x-csi";
		} else if (fileName.endsWith(".css")) {
			contentType = "text/css";
		} else if (fileName.endsWith(".cut")) {
			contentType = "application/x-cut";
		} else if (fileName.endsWith(".dbf")) {
			contentType = "application/x-dbf";
		} else if (fileName.endsWith(".dbm")) {
			contentType = "application/x-dbm";
		} else if (fileName.endsWith(".dbx")) {
			contentType = "application/x-dbx";
		} else if (fileName.endsWith(".dcd")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".dcx")) {
			contentType = "application/x-dcx";
		} else if (fileName.endsWith(".der")) {
			contentType = "application/x-x509-ca-cert";
		} else if (fileName.endsWith(".dgn")) {
			contentType = "application/x-dgn";
		} else if (fileName.endsWith(".dib")) {
			contentType = "application/x-dib";
		} else if (fileName.endsWith(".dll")) {
			contentType = "application/x-msdownload";
		} else if (fileName.endsWith(".doc")) {
			contentType = "application/msword";
		} else if (fileName.endsWith(".dot")) {
			contentType = "application/msword";
		} else if (fileName.endsWith(".drw")) {
			contentType = "application/x-drw";
		} else if (fileName.endsWith(".dtd")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".dwf")) {
			contentType = "Model/vnd.dwf";
		} else if (fileName.endsWith(".dwf")) {
			contentType = "application/x-dwf";
		} else if (fileName.endsWith(".dwg")) {
			contentType = "application/x-dwg";
		} else if (fileName.endsWith(".dxb")) {
			contentType = "application/x-dxb";
		} else if (fileName.endsWith(".dxf")) {
			contentType = "application/x-dxf";
		} else if (fileName.endsWith(".edn")) {
			contentType = "application/vnd.adobe.edn";
		} else if (fileName.endsWith(".emf")) {
			contentType = "application/x-emf";
		} else if (fileName.endsWith(".eml")) {
			contentType = "message/rfc822";
		} else if (fileName.endsWith(".ent")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".epi")) {
			contentType = "application/x-epi";
		} else if (fileName.endsWith(".eps")) {
			contentType = "application/x-ps";
		} else if (fileName.endsWith(".eps")) {
			contentType = "application/postscript";
		} else if (fileName.endsWith(".etd")) {
			contentType = "application/x-ebx";
		} else if (fileName.endsWith(".exe")) {
			contentType = "application/x-msdownload";
		} else if (fileName.endsWith(".fax")) {
			contentType = "image/fax";
		} else if (fileName.endsWith(".fdf")) {
			contentType = "application/vnd.fdf";
		} else if (fileName.endsWith(".fif")) {
			contentType = "application/fractals";
		} else if (fileName.endsWith(".fo")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".frm")) {
			contentType = "application/x-frm";
		} else if (fileName.endsWith(".g4")) {
			contentType = "application/x-g4";
		} else if (fileName.endsWith(".gbr")) {
			contentType = "application/x-gbr";
		} else if (fileName.endsWith(".")) {
			contentType = "application/x-";
		} else if (fileName.endsWith(".gif")) {
			contentType = "image/gif";
		} else if (fileName.endsWith(".gl2")) {
			contentType = "application/x-gl2";
		} else if (fileName.endsWith(".gp4")) {
			contentType = "application/x-gp4";
		} else if (fileName.endsWith(".hgl")) {
			contentType = "application/x-hgl";
		} else if (fileName.endsWith(".hmr")) {
			contentType = "application/x-hmr";
		} else if (fileName.endsWith(".hpg")) {
			contentType = "application/x-hpgl";
		} else if (fileName.endsWith(".hpl")) {
			contentType = "application/x-hpl";
		} else if (fileName.endsWith(".hqx")) {
			contentType = "application/mac-binhex40";
		} else if (fileName.endsWith(".hrf")) {
			contentType = "application/x-hrf";
		} else if (fileName.endsWith(".hta")) {
			contentType = "application/hta";
		} else if (fileName.endsWith(".htc")) {
			contentType = "text/x-component";
		} else if (fileName.endsWith(".htm")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".html")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".htt")) {
			contentType = "text/webviewhtml";
		} else if (fileName.endsWith(".htx")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".icb")) {
			contentType = "application/x-icb";
		} else if (fileName.endsWith(".ico")) {
			contentType = "image/x-icon";
		} else if (fileName.endsWith(".ico")) {
			contentType = "application/x-ico";
		} else if (fileName.endsWith(".iff")) {
			contentType = "application/x-iff";
		} else if (fileName.endsWith(".ig4")) {
			contentType = "application/x-g4";
		} else if (fileName.endsWith(".igs")) {
			contentType = "application/x-igs";
		} else if (fileName.endsWith(".iii")) {
			contentType = "application/x-iphone";
		} else if (fileName.endsWith(".img")) {
			contentType = "application/x-img";
		} else if (fileName.endsWith(".ins")) {
			contentType = "application/x-internet-signup";
		} else if (fileName.endsWith(".isp")) {
			contentType = "application/x-internet-signup";
		} else if (fileName.endsWith(".IVF")) {
			contentType = "video/x-ivf";
		} else if (fileName.endsWith(".java")) {
			contentType = "java/*";
		} else if (fileName.endsWith(".jfif")) {
			contentType = "image/jpeg";
		} else if (fileName.endsWith(".jpe")) {
			contentType = "image/jpeg";
		} else if (fileName.endsWith(".jpe")) {
			contentType = "application/x-jpe";
		} else if (fileName.endsWith(".jpeg")) {
			contentType = "image/jpeg";
		} else if (fileName.endsWith(".jpg")) {
			contentType = "image/jpeg";
		} else if (fileName.endsWith(".jpg")) {
			contentType = "application/x-jpg";
		} else if (fileName.endsWith(".js")) {
			contentType = "application/x-javascript";
		} else if (fileName.endsWith(".jsp")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".la1")) {
			contentType = "audio/x-liquid-file";
		} else if (fileName.endsWith(".lar")) {
			contentType = "application/x-laplayer-reg";
		} else if (fileName.endsWith(".latex")) {
			contentType = "application/x-latex";
		} else if (fileName.endsWith(".lavs")) {
			contentType = "audio/x-liquid-secure";
		} else if (fileName.endsWith(".lbm")) {
			contentType = "application/x-lbm";
		} else if (fileName.endsWith(".lmsff")) {
			contentType = "audio/x-la-lms";
		} else if (fileName.endsWith(".ls")) {
			contentType = "application/x-javascript";
		} else if (fileName.endsWith(".ltr")) {
			contentType = "application/x-ltr";
		} else if (fileName.endsWith(".m1v")) {
			contentType = "video/x-mpeg";
		} else if (fileName.endsWith(".m2v")) {
			contentType = "video/x-mpeg";
		} else if (fileName.endsWith(".m3u")) {
			contentType = "audio/mpegurl";
		} else if (fileName.endsWith(".m4e")) {
			contentType = "video/mpeg4";
		} else if (fileName.endsWith(".mac")) {
			contentType = "application/x-mac";
		} else if (fileName.endsWith(".man")) {
			contentType = "application/x-troff-man";
		} else if (fileName.endsWith(".math")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".mdb")) {
			contentType = "application/msaccess";
		} else if (fileName.endsWith(".mdb")) {
			contentType = "application/x-mdb";
		} else if (fileName.endsWith(".mfp")) {
			contentType = "application/x-shockwave-flash";
		} else if (fileName.endsWith(".mht")) {
			contentType = "message/rfc822";
		} else if (fileName.endsWith(".mhtml")) {
			contentType = "message/rfc822";
		} else if (fileName.endsWith(".mi")) {
			contentType = "application/x-mi";
		} else if (fileName.endsWith(".mid")) {
			contentType = "audio/mid";
		} else if (fileName.endsWith(".midi")) {
			contentType = "audio/mid";
		} else if (fileName.endsWith(".mil")) {
			contentType = "application/x-mil";
		} else if (fileName.endsWith(".mml")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".mnd")) {
			contentType = "audio/x-musicnet-download";
		} else if (fileName.endsWith(".mns")) {
			contentType = "audio/x-musicnet-stream";
		} else if (fileName.endsWith(".mocha")) {
			contentType = "application/x-javascript";
		} else if (fileName.endsWith(".movie")) {
			contentType = "video/x-sgi-movie";
		} else if (fileName.endsWith(".mp1")) {
			contentType = "audio/mp1";
		} else if (fileName.endsWith(".mp2")) {
			contentType = "audio/mp2";
		} else if (fileName.endsWith(".mp2v")) {
			contentType = "video/mpeg";
		} else if (fileName.endsWith(".mp3")) {
			contentType = "audio/mp3";
		} else if (fileName.endsWith(".mp4")) {
			contentType = "video/mpeg4";
		} else if (fileName.endsWith(".mpa")) {
			contentType = "video/x-mpg";
		} else if (fileName.endsWith(".mpd")) {
			contentType = "application/vnd.ms-project";
		} else if (fileName.endsWith(".mpe")) {
			contentType = "video/x-mpeg";
		} else if (fileName.endsWith(".mpeg")) {
			contentType = "video/mpg";
		} else if (fileName.endsWith(".mpg")) {
			contentType = "video/mpg";
		} else if (fileName.endsWith(".mpga")) {
			contentType = "audio/rn-mpeg";
		} else if (fileName.endsWith(".mpp")) {
			contentType = "application/vnd.ms-project";
		} else if (fileName.endsWith(".mps")) {
			contentType = "video/x-mpeg";
		} else if (fileName.endsWith(".mpt")) {
			contentType = "application/vnd.ms-project";
		} else if (fileName.endsWith(".mpv")) {
			contentType = "video/mpg";
		} else if (fileName.endsWith(".mpv2")) {
			contentType = "video/mpeg";
		} else if (fileName.endsWith(".mpw")) {
			contentType = "application/vnd.ms-project";
		} else if (fileName.endsWith(".mpx")) {
			contentType = "application/vnd.ms-project";
		} else if (fileName.endsWith(".mtx")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".mxp")) {
			contentType = "application/x-mmxp";
		} else if (fileName.endsWith(".net")) {
			contentType = "image/pnetvue";
		} else if (fileName.endsWith(".nrf")) {
			contentType = "application/x-nrf";
		} else if (fileName.endsWith(".nws")) {
			contentType = "message/rfc822";
		} else if (fileName.endsWith(".odc")) {
			contentType = "text/x-ms-odc";
		} else if (fileName.endsWith(".out")) {
			contentType = "application/x-out";
		} else if (fileName.endsWith(".p10")) {
			contentType = "application/pkcs10";
		} else if (fileName.endsWith(".p12")) {
			contentType = "application/x-pkcs12";
		} else if (fileName.endsWith(".p7b")) {
			contentType = "application/x-pkcs7-certificates";
		} else if (fileName.endsWith(".p7c")) {
			contentType = "application/pkcs7-mime";
		} else if (fileName.endsWith(".p7m")) {
			contentType = "application/pkcs7-mime";
		} else if (fileName.endsWith(".p7r")) {
			contentType = "application/x-pkcs7-certreqresp";
		} else if (fileName.endsWith(".p7s")) {
			contentType = "application/pkcs7-signature";
		} else if (fileName.endsWith(".pc5")) {
			contentType = "application/x-pc5";
		} else if (fileName.endsWith(".pci")) {
			contentType = "application/x-pci";
		} else if (fileName.endsWith(".pcl")) {
			contentType = "application/x-pcl";
		} else if (fileName.endsWith(".pcx")) {
			contentType = "application/x-pcx";
		} else if (fileName.endsWith(".pdf")) {
			contentType = "application/pdf";
		} else if (fileName.endsWith(".pdf")) {
			contentType = "application/pdf";
		} else if (fileName.endsWith(".pdx")) {
			contentType = "application/vnd.adobe.pdx";
		} else if (fileName.endsWith(".pfx")) {
			contentType = "application/x-pkcs12";
		} else if (fileName.endsWith(".pgl")) {
			contentType = "application/x-pgl";
		} else if (fileName.endsWith(".pic")) {
			contentType = "application/x-pic";
		} else if (fileName.endsWith(".pko")) {
			contentType = "application/vnd.ms-pki.pko";
		} else if (fileName.endsWith(".pl")) {
			contentType = "application/x-perl";
		} else if (fileName.endsWith(".plg")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".pls")) {
			contentType = "audio/scpls";
		} else if (fileName.endsWith(".plt")) {
			contentType = "application/x-plt";
		} else if (fileName.endsWith(".png")) {
			contentType = "image/png";
		} else if (fileName.endsWith(".png")) {
			contentType = "application/x-png";
		} else if (fileName.endsWith(".pot")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.endsWith(".ppa")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.endsWith(".ppm")) {
			contentType = "application/x-ppm";
		} else if (fileName.endsWith(".pps")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.endsWith(".ppt")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.endsWith(".ppt")) {
			contentType = "application/x-ppt";
		} else if (fileName.endsWith(".pr")) {
			contentType = "application/x-pr";
		} else if (fileName.endsWith(".prf")) {
			contentType = "application/pics-rules";
		} else if (fileName.endsWith(".prn")) {
			contentType = "application/x-prn";
		} else if (fileName.endsWith(".prt")) {
			contentType = "application/x-prt";
		} else if (fileName.endsWith(".ps")) {
			contentType = "application/x-ps";
		} else if (fileName.endsWith(".ps")) {
			contentType = "application/postscript";
		} else if (fileName.endsWith(".ptn")) {
			contentType = "application/x-ptn";
		} else if (fileName.endsWith(".pwz")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (fileName.endsWith(".r3t")) {
			contentType = "text/vnd.rn-realtext3d";
		} else if (fileName.endsWith(".ra")) {
			contentType = "audio/vnd.rn-realaudio";
		} else if (fileName.endsWith(".ram")) {
			contentType = "audio/x-pn-realaudio";
		} else if (fileName.endsWith(".ras")) {
			contentType = "application/x-ras";
		} else if (fileName.endsWith(".rat")) {
			contentType = "application/rat-file";
		} else if (fileName.endsWith(".rdf")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".rec")) {
			contentType = "application/vnd.rn-recording";
		} else if (fileName.endsWith(".red")) {
			contentType = "application/x-red";
		} else if (fileName.endsWith(".rgb")) {
			contentType = "application/x-rgb";
		} else if (fileName.endsWith(".rjs")) {
			contentType = "application/vnd.rn-realsystem-rjs";
		} else if (fileName.endsWith(".rjt")) {
			contentType = "application/vnd.rn-realsystem-rjt";
		} else if (fileName.endsWith(".rlc")) {
			contentType = "application/x-rlc";
		} else if (fileName.endsWith(".rle")) {
			contentType = "application/x-rle";
		} else if (fileName.endsWith(".rm")) {
			contentType = "application/vnd.rn-realmedia";
		} else if (fileName.endsWith(".rmf")) {
			contentType = "application/vnd.adobe.rmf";
		} else if (fileName.endsWith(".rmi")) {
			contentType = "audio/mid";
		} else if (fileName.endsWith(".rmj")) {
			contentType = "application/vnd.rn-realsystem-rmj";
		} else if (fileName.endsWith(".rmm")) {
			contentType = "audio/x-pn-realaudio";
		} else if (fileName.endsWith(".rmp")) {
			contentType = "application/vnd.rn-rn_music_package";
		} else if (fileName.endsWith(".rms")) {
			contentType = "application/vnd.rn-realmedia-secure";
		} else if (fileName.endsWith(".rmvb")) {
			contentType = "application/vnd.rn-realmedia-vbr";
		} else if (fileName.endsWith(".rmx")) {
			contentType = "application/vnd.rn-realsystem-rmx";
		} else if (fileName.endsWith(".rnx")) {
			contentType = "application/vnd.rn-realplayer";
		} else if (fileName.endsWith(".rp")) {
			contentType = "image/vnd.rn-realpix";
		} else if (fileName.endsWith(".rpm")) {
			contentType = "audio/x-pn-realaudio-plugin";
		} else if (fileName.endsWith(".rsml")) {
			contentType = "application/vnd.rn-rsml";
		} else if (fileName.endsWith(".rt")) {
			contentType = "text/vnd.rn-realtext";
		} else if (fileName.endsWith(".rtf")) {
			contentType = "application/msword";
		} else if (fileName.endsWith(".rtf")) {
			contentType = "application/x-rtf";
		} else if (fileName.endsWith(".rv")) {
			contentType = "video/vnd.rn-realvideo";
		} else if (fileName.endsWith(".sam")) {
			contentType = "application/x-sam";
		} else if (fileName.endsWith(".sat")) {
			contentType = "application/x-sat";
		} else if (fileName.endsWith(".sdp")) {
			contentType = "application/sdp";
		} else if (fileName.endsWith(".sdw")) {
			contentType = "application/x-sdw";
		} else if (fileName.endsWith(".sit")) {
			contentType = "application/x-stuffit";
		} else if (fileName.endsWith(".slb")) {
			contentType = "application/x-slb";
		} else if (fileName.endsWith(".sld")) {
			contentType = "application/x-sld";
		} else if (fileName.endsWith(".slk")) {
			contentType = "drawing/x-slk";
		} else if (fileName.endsWith(".smi")) {
			contentType = "application/smil";
		} else if (fileName.endsWith(".smil")) {
			contentType = "application/smil";
		} else if (fileName.endsWith(".smk")) {
			contentType = "application/x-smk";
		} else if (fileName.endsWith(".snd")) {
			contentType = "audio/basic";
		} else if (fileName.endsWith(".sol")) {
			contentType = "text/plain";
		} else if (fileName.endsWith(".sor")) {
			contentType = "text/plain";
		} else if (fileName.endsWith(".spc")) {
			contentType = "application/x-pkcs7-certificates";
		} else if (fileName.endsWith(".spl")) {
			contentType = "application/futuresplash";
		} else if (fileName.endsWith(".spp")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".ssm")) {
			contentType = "application/streamingmedia";
		} else if (fileName.endsWith(".sst")) {
			contentType = "application/vnd.ms-pki.certstore";
		} else if (fileName.endsWith(".stl")) {
			contentType = "application/vnd.ms-pki.stl";
		} else if (fileName.endsWith(".stm")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".sty")) {
			contentType = "application/x-sty";
		} else if (fileName.endsWith(".svg")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".swf")) {
			contentType = "application/x-shockwave-flash";
		} else if (fileName.endsWith(".tdf")) {
			contentType = "application/x-tdf";
		} else if (fileName.endsWith(".tg4")) {
			contentType = "application/x-tg4";
		} else if (fileName.endsWith(".tga")) {
			contentType = "application/x-tga";
		} else if (fileName.endsWith(".tif")) {
			contentType = "image/tiff";
		} else if (fileName.endsWith(".tif")) {
			contentType = "application/x-tif";
		} else if (fileName.endsWith(".tiff")) {
			contentType = "image/tiff";
		} else if (fileName.endsWith(".tld")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".top")) {
			contentType = "drawing/x-top";
		} else if (fileName.endsWith(".torrent")) {
			contentType = "application/x-bittorrent";
		} else if (fileName.endsWith(".tsd")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".txt")) {
			contentType = "text/plain";
		} else if (fileName.endsWith(".uin")) {
			contentType = "application/x-icq";
		} else if (fileName.endsWith(".uls")) {
			contentType = "text/iuls";
		} else if (fileName.endsWith(".vcf")) {
			contentType = "text/x-vcard";
		} else if (fileName.endsWith(".vda")) {
			contentType = "application/x-vda";
		} else if (fileName.endsWith(".vdx")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vml")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".vpg")) {
			contentType = "application/x-vpeg005";
		} else if (fileName.endsWith(".vsd")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vsd")) {
			contentType = "application/x-vsd";
		} else if (fileName.endsWith(".vss")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vst")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vst")) {
			contentType = "application/x-vst";
		} else if (fileName.endsWith(".vsw")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vsx")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vtx")) {
			contentType = "application/vnd.visio";
		} else if (fileName.endsWith(".vxml")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".wav")) {
			contentType = "audio/wav";
		} else if (fileName.endsWith(".wax")) {
			contentType = "audio/x-ms-wax";
		} else if (fileName.endsWith(".wb1")) {
			contentType = "application/x-wb1";
		} else if (fileName.endsWith(".wb2")) {
			contentType = "application/x-wb2";
		} else if (fileName.endsWith(".wb3")) {
			contentType = "application/x-wb3";
		} else if (fileName.endsWith(".wbmp")) {
			contentType = "image/vnd.wap.wbmp";
		} else if (fileName.endsWith(".wiz")) {
			contentType = "application/msword";
		} else if (fileName.endsWith(".wk3")) {
			contentType = "application/x-wk3";
		} else if (fileName.endsWith(".wk4")) {
			contentType = "application/x-wk4";
		} else if (fileName.endsWith(".wkq")) {
			contentType = "application/x-wkq";
		} else if (fileName.endsWith(".wks")) {
			contentType = "application/x-wks";
		} else if (fileName.endsWith(".wm")) {
			contentType = "video/x-ms-wm";
		} else if (fileName.endsWith(".wma")) {
			contentType = "audio/x-ms-wma";
		} else if (fileName.endsWith(".wmd")) {
			contentType = "application/x-ms-wmd";
		} else if (fileName.endsWith(".wmf")) {
			contentType = "application/x-wmf";
		} else if (fileName.endsWith(".wml")) {
			contentType = "text/vnd.wap.wml";
		} else if (fileName.endsWith(".wmv")) {
			contentType = "video/x-ms-wmv";
		} else if (fileName.endsWith(".wmx")) {
			contentType = "video/x-ms-wmx";
		} else if (fileName.endsWith(".wmz")) {
			contentType = "application/x-ms-wmz";
		} else if (fileName.endsWith(".wp6")) {
			contentType = "application/x-wp6";
		} else if (fileName.endsWith(".wpd")) {
			contentType = "application/x-wpd";
		} else if (fileName.endsWith(".wpg")) {
			contentType = "application/x-wpg";
		} else if (fileName.endsWith(".wpl")) {
			contentType = "application/vnd.ms-wpl";
		} else if (fileName.endsWith(".wq1")) {
			contentType = "application/x-wq1";
		} else if (fileName.endsWith(".wr1")) {
			contentType = "application/x-wr1";
		} else if (fileName.endsWith(".wri")) {
			contentType = "application/x-wri";
		} else if (fileName.endsWith(".wrk")) {
			contentType = "application/x-wrk";
		} else if (fileName.endsWith(".ws")) {
			contentType = "application/x-ws";
		} else if (fileName.endsWith(".ws2")) {
			contentType = "application/x-ws";
		} else if (fileName.endsWith(".wsc")) {
			contentType = "text/scriptlet";
		} else if (fileName.endsWith(".wsdl")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".wvx")) {
			contentType = "video/x-ms-wvx";
		} else if (fileName.endsWith(".xdp")) {
			contentType = "application/vnd.adobe.xdp";
		} else if (fileName.endsWith(".xdr")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xfd")) {
			contentType = "application/vnd.adobe.xfd";
		} else if (fileName.endsWith(".xfdf")) {
			contentType = "application/vnd.adobe.xfdf";
		} else if (fileName.endsWith(".xhtml")) {
			contentType = "text/html";
		} else if (fileName.endsWith(".xls")) {
			contentType = "application/vnd.ms-excel";
		} else if (fileName.endsWith(".xls")) {
			contentType = "application/x-xls";
		} else if (fileName.endsWith(".xlw")) {
			contentType = "application/x-xlw";
		} else if (fileName.endsWith(".xml")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xpl")) {
			contentType = "audio/scpls";
		} else if (fileName.endsWith(".xq")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xql")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xquery")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xsd")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xsl")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xslt")) {
			contentType = "text/xml";
		} else if (fileName.endsWith(".xwd")) {
			contentType = "application/x-xwd";
		} else if (fileName.endsWith(".x_b")) {
			contentType = "application/x-x_b";
		} else if (fileName.endsWith(".sis")) {
			contentType = "application/vnd.symbian.install";
		} else if (fileName.endsWith(".sisx")) {
			contentType = "application/vnd.symbian.install";
		} else if (fileName.endsWith(".x_t")) {
			contentType = "application/x-x_t";
		} else if (fileName.endsWith(".ipa")) {
			contentType = "application/vnd.iphone";
		} else if (fileName.endsWith(".apk")) {
			contentType = "application/vnd.android.package-archive";
		} else if (fileName.endsWith(".xap")) {
			contentType = "application/x-silverlight-app";
		} else {
			contentType = "application/octet-stream";
		}
		return contentType;
	}
}

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 保留Annotation不混淆
-keepattributes *Annotation*

# 避免混淆泛型
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault


# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable






-keep class java.** { *; }
-keep class javax.** { *; }
-keep class com.sun.org.** { *; }



# missing_rules.txt
-dontwarn javax.naming.NamingEnumeration
-dontwarn javax.naming.NamingException
-dontwarn javax.naming.directory.Attribute
-dontwarn javax.naming.directory.Attributes
-dontwarn javax.naming.directory.DirContext
-dontwarn javax.naming.directory.InitialDirContext
-dontwarn javax.naming.directory.SearchControls
-dontwarn javax.naming.directory.SearchResult
-dontwarn com.android.org.conscrypt.SSLParametersImpl
-dontwarn javax.xml.bind.DatatypeConverter
-dontwarn org.apache.harmony.xnet.provider.jsse.SSLParametersImpl
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
-dontwarn com.sun.org.apache.commons.collections.FastHashMap
-dontwarn com.sun.org.apache.commons.logging.Log
-dontwarn com.sun.org.apache.commons.logging.LogFactory
-dontwarn java.beans.BeanInfo
-dontwarn java.beans.IndexedPropertyDescriptor
-dontwarn java.beans.IntrospectionException
-dontwarn java.beans.Introspector
-dontwarn java.beans.PropertyDescriptor
-dontwarn com.sun.org.apache.commons.collections.comparators.ComparableComparator
-dontwarn sun.misc.**
-dontwarn junit.textui.TestRunner

# 保留support下的所有类及其内部类
-keep class android.** {*;}
-keep interface android.**
-keep class androidx.** {*;}
-keep interface androidx.**

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends androidx.lifecycle.*

# 保留R下面的资源
-keep class **.R$* {*;}

# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留Parcelable序列化类不被混淆
-keep class * implements android.os.Parcelable { *; }

# 保留Serializable序列化的类不被混淆
-keep class * implements java.io.Serializable { *; }

# kotlin
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-dontwarn coil3.PlatformContext

# retrofit2
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keep,allowobfuscation interface <1>
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>
-keep,allowobfuscation,allowshrinking class retrofit2.Response


# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
# 保留 Gson 类
-keep class com.google.gson.Gson { *; }
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class com.google.gson.TypeAdapter { *; }
-keep class com.google.gson.TypeAdapterFactory { *; }
-keep class com.google.gson.JsonSerializer { *; }
-keep class com.google.gson.JsonDeserializer { *; }
-keep class com.google.gson.JsonObject { *; }
-keep class com.google.gson.JsonParser

# utilcode
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class * {
    @com.blankj.utilcode.util.BusUtils$Bus <methods>;
}
-keep public class * extends com.blankj.utilcode.util.ApiUtils$BaseApi
-keep,allowobfuscation @interface com.blankj.utilcode.util.ApiUtils$Api
-keep @com.blankj.utilcode.util.ApiUtils$Api class *

#MMKV
-keep class com.tencent.** {*;}
-keep class com.tencent.mmkv.** {*;}

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#rive
-keep class app.rive.runtime.kotlin.** { *; }

-keep class **ViewBinding { *; }
-keep public class * implements android.view.ViewBinding { *; }
-keepclassmembers class * implements android.view.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * inflate(android.view.LayoutInflater, android.view.ViewGroup, boolean);
    public static * bind(android.view.View);
}



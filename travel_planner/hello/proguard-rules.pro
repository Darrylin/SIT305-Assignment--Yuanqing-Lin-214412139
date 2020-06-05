##Optimized execution through a specified number
#-optimizationpasses 5
##No confusion of class names when confused
##-dontusemixedcaseclassnames
##
#-dontskipnonpubliclibraryclasses
##-dontpreverify
#-verbose
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#
## modify
#-keep public class * extends android.view.View {
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#    public void set*(...);
#}
#
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#
#-keepclasseswithmembernames class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}
#
##
##-keepclassmembers class * {
##   public <methods>;
##}
#
#-keepattributes *Annotation*
#
#-libraryjars libs/zlsdk.aar
#-dontwarn android.support.annotation.**
#-keep class android.support.annotation.** { *; }
#-keep public class * extends android.support.annotation.**
#-keep public class * extends android.app.Fragment
#
##butterknife
#-keep class butterknife.** { *; }
#-dontwarn butterknife.internal.**
#-keep class **$$ViewBinder { *; }
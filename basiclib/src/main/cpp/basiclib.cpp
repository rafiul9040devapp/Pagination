#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_rafiul_basiclib_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "https://api.quotable.io";
    return env->NewStringUTF(hello.c_str());
}
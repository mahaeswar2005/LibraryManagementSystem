/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class AdminLibraryManagement */

#ifndef _Included_AdminLibraryManagement
#define _Included_AdminLibraryManagement
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     AdminLibraryManagement
 * Method:    addBook
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_AdminLibraryManagement_addBook
  (JNIEnv *, jobject, jstring, jstring, jstring);

/*
 * Class:     AdminLibraryManagement
 * Method:    removeBook
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_AdminLibraryManagement_removeBook
  (JNIEnv *, jobject, jstring);

/*
 * Class:     AdminLibraryManagement
 * Method:    loadUsers
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_AdminLibraryManagement_loadUsers
  (JNIEnv *, jobject);

/*
 * Class:     AdminLibraryManagement
 * Method:    registerUser
 * Signature: (Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_AdminLibraryManagement_registerUser
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     AdminLibraryManagement
 * Method:    updateUserProfile
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_AdminLibraryManagement_updateUserProfile
  (JNIEnv *, jobject, jstring, jstring, jstring);

#ifdef __cplusplus
}
#endif
#endif
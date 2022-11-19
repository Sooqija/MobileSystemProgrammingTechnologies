#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("a08_logic_integration");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("a08_logic_integration")
//      }
//    }

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

class Counter {
private:
    int counter;
public:
    Counter() {
        counter = 42;
    };
    Counter(int local_counter) {
        counter = local_counter;
    };
    int getCounter() {
        return counter;
    }
    void increaseCounter () {
        counter++;
    }
    void resetCounter () {
        counter = 0;
    }
};


class StringList {
private:
    std::vector<std::string> list;
    std::string result;
    bool is_added;

public:
    StringList () = default;

    bool push(const std::string& element) {
        std::string toup;
        std::string tolow;
        std::string toad = "";
        if (element.size() != 0) {
            toup = element.substr(0, 1);
            for (auto & c: toup) toad += toupper(c);
            tolow = element.substr(1, element.size()-1);
            for (auto & c: tolow) toad += tolower(c);
        }
        if (std::find(list.begin(), list.end(), toad) != list.end()) {} else {
            list.emplace_back(toad);

            if (list.size() == 1) {
                if (list[0].size() != 0) {
                    toup = list[0].substr(0, 1);
                    for (auto & c: toup) result += toupper(c);
                    tolow = list[0].substr(1, list[0].size()-1);
                    for (auto & c: tolow) result += tolower(c);
                }
            } else {
                tolow = element.substr(0, element.size());
                result += ", ";
                for (auto &c: tolow) result += tolower(c);
            }
            is_added = true;
            return true;
        }
        is_added = false;
        return false;
    }

    void pop() {
        if (list.size() == 1) {
            result = "";
        } else {
            result = result.substr(0, result.size() - list[list.size()-1].size() - 2);
        }
        list.pop_back();
    }

    const char * get(int position) {
        return list[position].c_str();
    }

    const char * getAll() {
        return result.c_str();
    }

    const bool getIsAdded() {
        return is_added;
    }
};

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_MainActivity_createCounter(JNIEnv *env, jclass clazz) {
    return (jlong) (new Counter());
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_a08_1logic_1integration_MainActivity_getCounter(JNIEnv *env, jclass clazz, jlong nativePointer) {
    return ((Counter*)nativePointer)->getCounter();
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_MainActivity_increaseCounter(JNIEnv *env, jclass clazz, jlong nativePointer) {
    Counter* pointer = ((Counter*)nativePointer);
    pointer->increaseCounter();
    return reinterpret_cast<jlong>(pointer);
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_MainActivity_resetCounter(JNIEnv *env, jclass clazz, jlong nativePointer) {
    Counter* pointer = ((Counter*)nativePointer);
    pointer->resetCounter();
    return reinterpret_cast<jlong>(pointer);
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_createStringList(JNIEnv *env,
                                                                             jclass clazz) {
    return (jlong) (new StringList());
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_pushStringList(JNIEnv *env,
                                                                           jclass clazz,
                                                                           jstring element,
                                                                           jlong nativePointer) {
    StringList* pointer = ((StringList*)nativePointer);
    pointer->push(env->GetStringUTFChars(element, nullptr));
    return reinterpret_cast<jlong> (pointer);
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_popStringList(JNIEnv *env, jclass clazz,
                                                                          jlong nativePointer) {
    StringList* pointer = ((StringList*)nativePointer);
    pointer->pop();
    return reinterpret_cast<jlong>(pointer);
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_getStringList(JNIEnv *env, jclass clazz,
                                                                          jint position,
                                                                          jlong nativePointer) {
    return env->NewStringUTF((((StringList*)nativePointer)->get(position)));
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_getAllStringList(JNIEnv *env,
                                                                             jclass clazz,
                                                                             jlong nativePointer) {
    return env->NewStringUTF((((StringList*)nativePointer)->getAll()));
}
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_a08_1logic_1integration_StringListActivity_getIsAdded(JNIEnv *env, jclass clazz,
                                                                       jlong nativePointer) {
    return ((StringList*)nativePointer)->getIsAdded();
}
# Graal VM

 [Homepage](https://www.graalvm.org/) | [Documentation](https://www.graalvm.org/docs/) | [Github](https://github.com/oracle/graal)

## Native Image

### Scala 2.13.x support

See [scala/bug#11634](https://github.com/scala/bug/issues/11634), you will need a substitution for `scala.runtime.Statics`:

```java
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "scala.runtime.Statics")
final class Target_scala_runtime_Statics {

    @Substitute
    public static void releaseFence() {
        UnsafeUtils.UNSAFE.storeFence();
    }
}
```

```java
import java.lang.reflect.Field;

class UnsafeUtils {
    static final sun.misc.Unsafe UNSAFE;

    static {
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) field.get(null);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
```

### Size of generated image

Currently not quite optimized it seems. See [oracle/graal#287](https://github.com/oracle/graal/issues/287) for ongoing discussion about
that.

### Generating heap dumps for substrate VM at runtime

@@@warning
Currently not possible for CE!
@@@

[Documentation for EE](https://www.graalvm.org/docs/reference-manual/native_heapdump/)

### Print heap histogram from inside binary

Similar to what `jmap -histo` would do on openjdk but from inside the process. Would probably make sense to trigger GC right before that
if you are only interested in live objects.

(as of CE 20.1.0)

```java
import com.oracle.svm.core.heap.ClassHistogramVisitor;
import com.oracle.svm.core.heap.Heap;
import com.oracle.svm.core.log.Log;
import com.oracle.svm.core.thread.JavaVMOperation;


JavaVMOperation.enqueueBlockingSafepoint("histo", () -> {
    ClassHistogramVisitor vis = ClassHistogramVisitor.factory();
    Heap.getHeap().walkImageHeapObjects(vis);
    vis.toLogByCount(Log.log(), 0, false);
});
```
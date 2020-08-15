# Graal VM

 [Homepage](https://www.graalvm.org/) | [Documentation](https://www.graalvm.org/docs/) | [Github](https://github.com/oracle/graal)

## Native Image

### Size of generated image

Currently not quite optimized it seems. See [graal/issues#287](https://github.com/oracle/graal/issues/287) for ongoing discussion about
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
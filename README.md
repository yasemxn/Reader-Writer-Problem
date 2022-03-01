# Reader-Writer-Problem

Operating Systems
	•	Explanation of Code

	•	class ReadWriteLock

In this class, there are two semaphores such as readSemaphore and writeSemaphore. This semaphores locks the threads with acquire and release methods to block the other threads to enter while in process.

isWriting is to check if the thread is locked or not. 

	•	readLock() 

The thread is locked with readSemaphore.acquire() and the thread is reading. Meanwhile, isWriting is true so other threads can not join and write.

And if isWriting equals to true, the writeSemaphore thread is also locked with writeSemaphore.acquire(). Due to that, any additional writer can’t perform write function. Lastly, The current thread name that is on the process is printed on the console.

ii.    writeLock() 

The thread is locked with writeSemaphore.acquire() and the thread is writing. Due to that, no reader is allowed to read. The current thread name that is on the process is printed on the console.

iii.   readUnlock()

The reading is done, isWriting is false to open the way for other threads the write. If the isWriting equals to false, the thread’s writing is unlocked by writeSemaphore.release() so now other additional threads can enter and perform write function. And reading is also unlocked by readSemaphore.release(). So other readers may read as well. 

iv.  writeUnlock()

The writing is done and isWriting becomes false to unlock the thread with semaphore. The thread is unlocked with writeSemaphore.release(). Owing to that, other threads can join the process.
In class Writer and class Reader, the threadSleep.nap() functions are added which comes from the threadSleep class. 
	•	class threadSleep

In this class, for threads to work randomly and make threads seem like they are doing a task, a nap time is used. This is for efficiency.

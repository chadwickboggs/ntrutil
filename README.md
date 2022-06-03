## Introduction
Crypto may be used as a library to encrypt/decrypt data or called as a command
line tool which reads/writes to/from stdin/stdout.

## Library Methods
* com.tiffanytimbric.crypto.ntrutil.NtrUtil
  * encrypt(...)
  * decrypt(...)
* com.tiffanytimbric.crypto.nooputil.XorUtil
  * encrypt(...)
  * decrypt(...)
* com.tiffanytimbric.crypto.nooputil.NoopUtil
  * encrypt(...)
  * decrypt(...)

## Command Line Usage
Please read usage.txt which you will find within the conf folder.

## Usage Examples

    `$ echo 'Hello, World!' | bin/crypto -c XOR -e | bin/crypto -c XOR -d`

    `$ wc -l lorem_ipsum_100.txt`

    `$ cat lorem_ipsum_100.txt | bin/crypto -c XOR -e | bin/crypto -c XOR -d | wc -l`
    `$ cat lorem_ipsum_100.txt | bin/xorutil -e | bin/xorutil -d | wc -l`

    `$ cat lorem_ipsum_100.txt | bin/crypto -c NTRU -e | bin/crypto -c NTRU -d | wc -l`
    `$ cat lorem_ipsum_100.txt | bin/ntrutil -e | bin/ntrutil -d | wc -l`

## Code Analysis
This Java code includes two separate implementation techiques for
multi-threaded/concurrent processing, one using the JDK's ExecutorServices and
another using RxJava 3.x.  The RxJava implementation is less readable,
less concise.  The significant additional features RxJava offers are not used
in this application as they are not needed.  RxJava offers no benefits to this
application.  It was introduced merely as a coding exercise.
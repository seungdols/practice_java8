# ClosureExamples3 - Lambda Expression 내부구현 및 Closure 

람다표현식이 기존 Anonymous Class의 유사한 구현체로 이루어진 것이 아니라 내부 구현 자체가 다르다. 

아래 코드는 ClosureExamples3의 코드이다. 

```java
public class ClosureExamples3 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };

        runnable.run();

        Runnable runnable1 = () -> System.out.println(number);
        runnable1.run();

    }

}
```

아래는 위 코드의 Byte Code View plugin으로 확인한 바이트 코드이다.


```java
// class version 52.0 (52)
// access flags 0x21
public class com/seungdols/java/closure/ClosureExamples3 {

  // compiled from: ClosureExamples3.java
  // access flags 0x8
  static INNERCLASS com/seungdols/java/closure/ClosureExamples3$1 null null
  // access flags 0x19
  public final static INNERCLASS java/lang/invoke/MethodHandles$Lookup java/lang/invoke/MethodHandles Lookup

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 8 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lcom/seungdols/java/closure/ClosureExamples3; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static main([Ljava/lang/String;)V
   L0
    LINENUMBER 11 L0
    INVOKESTATIC com/seungdols/java/closure/ClosureExamples3.test ()V
   L1
    LINENUMBER 12 L1
    RETURN
   L2
    LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
    MAXSTACK = 0
    MAXLOCALS = 1

  // access flags 0xA
  private static test()V
   L0
    LINENUMBER 15 L0
    BIPUSH 100
    ISTORE 0
   L1
    LINENUMBER 17 L1
    NEW com/seungdols/java/closure/ClosureExamples3$1
    DUP
    ILOAD 0
    INVOKESPECIAL com/seungdols/java/closure/ClosureExamples3$1.<init> (I)V
    ASTORE 1
   L2
    LINENUMBER 24 L2
    ALOAD 1
    INVOKEINTERFACE java/lang/Runnable.run ()V
   L3
    LINENUMBER 26 L3
    ILOAD 0
    INVOKEDYNAMIC run(I)Ljava/lang/Runnable; [
      // handle kind 0x6 : INVOKESTATIC
      java/lang/invoke/LambdaMetafactory.metafactory(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      // arguments:
      ()V, 
      // handle kind 0x6 : INVOKESTATIC
      com/seungdols/java/closure/ClosureExamples3.lambda$test$0(I)V, 
      ()V
    ]
    ASTORE 2
   L4
    LINENUMBER 27 L4
    ALOAD 2
    INVOKEINTERFACE java/lang/Runnable.run ()V
   L5
    LINENUMBER 29 L5
    RETURN
   L6
    LOCALVARIABLE number I L1 L6 0
    LOCALVARIABLE runnable Ljava/lang/Runnable; L2 L6 1
    LOCALVARIABLE runnable1 Ljava/lang/Runnable; L4 L6 2
    MAXSTACK = 3
    MAXLOCALS = 3

  // access flags 0x100A
  private static synthetic lambda$test$0(I)V
   L0
    LINENUMBER 26 L0
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    ILOAD 0
    INVOKEVIRTUAL java/io/PrintStream.println (I)V
    RETURN
   L1
    LOCALVARIABLE number I L0 L1 0
    MAXSTACK = 2
    MAXLOCALS = 1
}
```

중요한 차이가 존재하는데, Anonymous Class는 내부적으로 익명의 class를 만들어 해당 클래스를 실행시킨다 
그러나, 람다 표현식의 경우 클래스 생성이 없이, Compile-time시에는 JVM이 참조 할 수 있는 Method Handle에 어떤 명세를 해둔다.
컴파일 시점까지는 그것이 어떤 실행인지 알 수 없다. (JDK7에서 도입, Jruby, Jpython etc 호환)

그리고, JVM은 Method Handle에서 메소드를 명세를 보고 타입과 해당 메소드를 만든다. (맞으려나...?)

부가적으로 오라클에서 추정하는바로는 Anonymous Class보다 Lambda Expression이 내부 구현 차이로 인해 더 빠르고, Lexical Scope 사용등 이점이 많다고 한다. 
(영상에서 케빈님 말로는 60배 빨라질수도 있다고 발표한바 있다고 소개.)

고로, Java8을 쓴다면 Anonymous Class보다는 Lambda Expression을 쓰는게 좋다고 하셨다.
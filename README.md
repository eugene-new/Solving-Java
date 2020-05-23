# Solving-Java
Problem Solving in Java
자바로는 주로 백준과 SWEA의 문제들을 풀고 있습니다  

## sort()

Arrays.sort() : 배열 정렬

Collections.sort() : List Collection 정렬 ex) ArrayList, LinkedList, Vector

## Interface Comparable

- 기본적으로 적용되는 정렬 기준이 되는 메서드를 정의
- 기본적으로 오름차순
- 구현 방법

    1) 정렬할 객체에 Comparable interface implements 

    2) compareTo() 메서드 오버라이드

     리턴 값이 음수, 0 ⇒ 그대로

    리턴 값이 양수 ⇒ 두 객체 swap

    ```
    if(this.o > param.o) return 1 // 오름차순
    if(this.o < param.o) return 1 // 내림차순
    else return -1
    ```

- 예시

```java
@Override
// 오름차 순 (어린거부터)
public int compareTo(Student target) {
		// this의 나이가 target의 나이보다 많으면 swap => 오름차순
    return this.age >= target.age ? 1 : - 1; 
 }

```

```java
// x좌표가 증가하는 순, 같으면 y좌표가 감소하는 순으로 정렬
class Point implements Comparable<Point> {
    int x, y;
    @Override
    public int compareTo(Point p) {
        if(this.x > p.x) {
            return 1; // x에 대해서는 오름차순
        }
        else if(this.x == p.x) {
            if(this.y < p.y) { // y에 대해서는 내림차순
                return 1;
            }
        }
        return -1;
    }
}

https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html
```

## Interface Comparator

- 정의 : 기본 정렬 기준과 다르게 정렬하고 싶을 때 사용

    주로 내림차순 정렬로 바꿀 때

- 구현

    1 ) Comparator interface implements

    2) compare() 메서드 오버라이드

    ```java
    // x좌표가 증가하는 순, 같으면 y좌표가 감소하는 순
    Comparator<Point> myComparator = new Comparator<Point>() {
      @Override
      public int compare(Point p1, Point p2) {
    		if(p1.x > p2.x) return 1;
    		else if(p1.x == p2.x) {
    			if(p1.y < p2.y) return 1;
    		}

    		return -1;
    	}
    };

    // main
    Collections.sort(pointList, myComparator);
    ```

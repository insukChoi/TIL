# RxJava 프로그래밍 - 리액티브 프로그래밍

## RxJava

서버 다수와 통신하게 되면 API 호출 각각에 콜백을 추가하게 된다.

콜백이 늘어나면 애플리케이션의 복잡성도 증가(callback hell)하게 된다.

RxJava는 자바로 리액티브 프로그래밍을 할 수 있는 라이브러리이며 비동기 프로그래밍과 함수형 프로그래밍 기법을 함께 활용한다.

RxJava2는 Maybe나 Flowable 같은 새로운 클래스가 추가되었고, 비동기 테스팅도 자연스럽게 할 수 있게 됐다.

리액티브 프로그래밍은 복잡한 비동기 프로그램을 쉽게 만들 수 있게 해준다. 리액티브의 의미에서 알 수 있듯이 이벤트(스크린 터치, 마우스 클릭, 키 입력, 서버의 비동기 응답)에
소비자가 비동기로 반응하여 처리한다. **또한 비동기에서 처리하기 힘든 에러 처리나 데이터 가공을 쉽게 할 수 있게 도와주기도 한다. 이벤트를 콜백이 아닌 데이터의 모음으로 모델링하기 때문이다.**

RxJava 라이브러리는 리액티브 프로그래밍 개념의 자바 구현체이다. RxJava 라이브러리는 1.x 버전을 거쳐 2016년에 전면 개편된 RxJava2.0을 출시했다.

## 리액티브 프로그래밍

리액티브 프로그래밍은 **데이터 흐름과 전달에 관한 프로그래밍 패러다임**이다. 기존의 명령형(imperative) 프로그래밍은 주로 컴퓨터 하드웨어를 대상으로 프로그래머가 작성한 코드가 정해진 절차에 따라 순서대로
실행된다. 그러나 리액티브 프로그래밍은 데이터 흐름을 먼저 정의하고 데이터가 변경되었을때 연관되는 함수나 수식이 업데이트되는 방식이다.

리액티브 프로그래밍을 가장 쉽게 이해할 수 있는 예는 엑셀이다. 이 프로그램은 각 cell에 값을 넣거나 혹은 다른 cell을 조합해서 원하는 값을 계산한다. 예를 들어 올해 1월부터 12월까지의 매출액의 합을
구한다고 가정한다. 매월 매출액은 리액티브 프로그래밍의 데이터 소스(data source)에 해당한다. 특정 월 매출액의 변경이 생길때 리액티브 프로그래밍은 변경된 매출액을 다시 가져와서 총합을 구하는 방식이 아니라
매월 매출액으로 지정해놓은 데이터 소스에서 변경된 값을 전달한다. 전달된 변경 개별 값이 미리 지정해둔 수식을 통해 계산되어 연말 매출액을 갱신하게 된다.

**명령형 프로그래밍 방식은 변경이 발생했다는 통지를 받아서 연말 매출액을 새로 계산하는 당겨오는(pull) 방식이지만, 리액티브 프로그래밍은 데이터 소스가 변경된 데이터를 밀어주는(push) 방식이다.** 일종의
옵저버 패턴이다.

리액티브 프로그래밍은 새로운 프로그래밍 방식이 아니다. 이미 1990년대에 제시된 개념이다. **컴퓨터 프로그래밍에서는 모델의 값에 변화가 생겼을때 뷰를 자동으로 업데이트해주는 목적으로 사용했다.** 앞서 연말
매출액 예에서는 매월 매출액이 모델이 된다. 또한 하드웨어 분야에서는 전자회로 일부 소자의 속성값이 변경되었을때 전체 회로에 미치는 영향을 바로 알 수 있는 HDL(Hardware Description
Language)이 리액티브 프로그래밍이다. **어떤 기능이 직접 실행되는 것이 아니라 시스템에 어떤 이벤트가 발생했을때 처리하는 것이다.**

네트워크 프로그래밍할때 사용하는 콜백(callback)이나 UI 프로그래밍할때 버튼 이벤트를 처리하는 클릭 리스너도 개념상으로는 리액티브 프로그래밍에 해당한다. 이 전통적인 개념에 몇가지 요소를 추가해야 RxJava
기반의 리액티브 프로그래밍이라고 할 수 있다.

## Observable 클래스

<img width="675" alt="스크린샷 2022-12-12 22 35 14" src="https://user-images.githubusercontent.com/14847562/207060926-ab218d41-2ee0-4013-b4e1-ce8cb0d48041.png">

## Hot Observable

Observable에는 Hot Observable과 Cold Observable이 있다.

Cold Observable은 마치 냉장고에 들어있는 냉동식품과 같다. Observable을 선언하고 just(), fromIterable() 함수를 호출해도 옵저버가 subscribe() 함수를 호출하여 구독하지
않으면 데이터를 발행하지 않는다. 다른말로 lazy 한 접근법이다.

Hot Observable은 구독자가 존재여부와 관계없이 데이터를 발행하는 Observable이다. 따라서 여러 구독자를 고려할 수 있다. 단, 구독자로서는 Observable에서 발행하는 데이터를 처음부터 모두
수신할 것을 보장할 수 없다. 즉, Cold Observable은 (구독자가) 구독하면 준비된 데이터를 처음부터 발행한다. 하지만 뜨거운 Observable은 구독한 시점부터 Observable에서 발행한 값을
받는다.

Cold Observable의 예는 웹 요청, 데이터베이스 쿼리와 파일 읽기 등이다. 보통 내가 원하는 URL이나 데이터를 지정하면 그때부터 서버나 데이터베이스 서버에 요청을 보내고 결과를 받아온다.

보통 Observable은 모두 Cold Observable 이다. 변도의 언급이 없으면 Cold Observable이라고 생각하면 된다. 한편 Hot Observable의 예는 마우스 이벤트, 키보드 이벤트,
시스템 이벤트, 센서 데이터와 주식 가격 등이 있다. 온도, 습도 센서의 데이터를 처리하는 앱이라면 최근의 온도, 습도 정보만 사용자에게 표시하면 된다.

**구독자가 여러 명이라는 것은 무슨 의미일까?**<br>
RxJava는 "구독자가 여러명이다"라는 뜻을 제대로 파악하는 것이 어렵다. 예를 들어 서버에 요청한 결과로 반환된 JSON 문서를 파싱해 원하는 속성을 추출한다고 해본다. 날씨 정보, 지역 정보, 시간 정보를
반환하는 경우 RxJava에서는 위의 세가지 정보를 구독자라고 생각하면 편리하다. 데이터의 원천은 한 곳이지만 최종적으로 원하는 결과 데이터가 여러 종류일 때는 각각을 구독자로 생각하면 좋다.

Hot Observable에는 주의할 점이 있다. 배압(back pressure)를 고려해야 한다는 점이다. 배압은 Observable에서 데이터를 발행하는 속도와 구독자가 처리하는 속도의 차이가 클때 발생한다.
기존 RxJava 1.x에서는 Observable 클래스에 별도의 배압 연산자들을 제공했지만 RxJava2에서는 Flowable이라는 특화 클래스에 배압을 처리한다.

**Cold Observable을 뜨거운 Observable 객체로 변환하는 방법은 Subject 객체를 만들거나 ConnectableObservable 클래스를 활용하는 것이다.**

## Reactive Streams

[Reactive Streams](http://www.reactive-streams.org/)

[Reactive Streams Specification for the Jvm](https://github.com/reactive-streams/reactive-streams-jvm)

## RxJava 기본구조

| 구분                  | 생산자        | 소비자        |
|---------------------|------------|------------|
| Reactive Stream 지원  | Flowable   | Subscriber |
| Reactive Stream 미지원 | Observable | Observer   |

Flowable 은 Reactive Streams 의 생산자인 Publisher 를 구현한 클래스고, Subscriber 는 Reactive Streams 의 클래스이다.<br>
그래서 기본적인 매커니즘은 Reactive Streams 와 같다. 생산자인 Flowable 로 구독 시작(onSubscribe), 데이터 통지(onNext), 에러 통지(onError), 완료 통지(
onComplete) 를 하고 각 통지를 받은 시점에 소비자인 Subscriber 로 처리합니다.<br>
그리고 Subscription 으로 데이터 개수 요청과 구독 해지를 합니다.

이베 비해 RxJava 2.x 버전의 observable 과 Observer 구성은 Reactive Streams 를 구현하지 않아서 Reactive Streams 인터페이스를 사용하지 않습니다. <br>
하지만 기본적인 메커니즘은 Flowable 과 Subscriber 구성과 거의 같습니다. <br>
생산자인 Observable 에서 구독 시작(onSubscribe), 데이터 통지(onNext), 에러 통지(onError), 완료 통지(onComplete) 를 하면 Observer 에서 이 통지를
받습니다. <br>
다만, Observable 과 Observer 구성은 통지하는 데이터 개수를 제어하는 배압 기능이 없기 때문에 데이터 개수를 요청하지 않습니다. <br>
그래서 Subscription 을 사용하지 않고, Disposable 이라는 구독 해지 메서드가 있는 인터페이스를 사용합니다. <br>
이 Disposable 은 구독을 시작하는 시점에 onSubscribe 메서드의 인자로 Observer 에 전달됩니다. Disposable 에는 구독 해지를 위한 다음 2개의 메서드가 있습니다.

| 메서드        | 설명                                    |
|------------|---------------------------------------|
| dispose    | 구독을 해지한다.                             |
| isDisposed | 구독을 해지하면 true, 해지하지 않으면 false 를 반환한다. |

그래서 Observable 과 Observer 간에 데이터를 교환할 때 Flowable 과 Subscriber 처럼 데이터 개수 요청은 하지 않고 데이터가 생성되자마자 Observer 에 통지됩니다.

## Cold 생산자와 Hot 생산자

<img src="https://user-images.githubusercontent.com/14847562/208289034-236bccfe-eaba-40f2-bcf8-6103731ce40c.jpeg" width="700" height="700" alt="coldAndHot"/>

## Flowable vs Observable

* Flowable 사용
    * 대량 데이터(10,000건 이상)를 처리할 때
    * 네트워크 통신이나 데이터베이스 등의 I/O 처리를 할 때
* Observable
    * GUI 이벤트
    * 소량 데이터(1,000건 이하)를 처리할 때
    * 데이터 처리가 기본으로 동기 방식이며, 자바 표준의 Stream 대신 사용할 때

## Single / Maybe / Completable

| 클래스         | 설명                                                |
|-------------|---------------------------------------------------|
| Single      | 데이터를 1건만 통지하거나 에러를 통지하는 클래스                       |
| Maybe       | 데이터를 1건만 통지하거나 1건도 통지하지 않고 완료를 통지하거나 에러를 통지하는 클래스 |
| Completable | 데이터를 1건도 통지하지 않고 완료를 통지하거나 에러를 통지하는 클래스           |

이 클래스들은 Flowable 과 Observable 처럼 데이터 통지와 완료 통지가 나누어져 있지 않고 데이터 통지 자체가 완료를 의미하거나
데이터 통지 없이 완료 통지만 합니다. 그리고 각 클래스의 통지에 대응하는 소비자가 필요한데, 일반적인 Subscriber 와 Observer 는 사용하지 못하고
독자적인 소비자를 제공한다.

| 생산자         | 소비자                 |
|-------------|---------------------|
| Single      | SingleObserver      |
| Maybe       | MaybeObserver       |
| Completable | CompletableObserver |

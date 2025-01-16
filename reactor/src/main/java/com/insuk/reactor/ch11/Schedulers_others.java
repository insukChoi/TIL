package com.insuk.reactor.ch11;

/**
 * - [Schedulers.boundedElastic()]
 * ExecutorService 기반의 스레드 풀을 생성한 후, 그 안에서 정해진 수만큼의 스레드를 사용하여 작업을 처리하고
 * 작업이 종료된 스레드는 반납하여 재사용하는 방식입니다.
 *
 * 기본적으로 CPU 코어 수 x 10 만큼의 스레드를 생성하며, 풀에 있는 모든 스레드가 작업을 처리하고 있다면 이용 가능한 스레드가 생길 때까지 최대
 * 100,000개의 작업이 큐에서 대기할 수 있습니다.
 *
 * Schedulers.boundedElastic()은 바로 이러한 Blocking I/O 작업을 효과적으로 처리하기 위한 방식입니다.
 */

/**
 * - [Schedulers.parallel()]
 * Schedulers.boundedElastic()이 Blocking I/O 작업에 최적화되어 있는 반면에,
 * Schedulers.parallel()은 Non-Blocking I/O에 최적화되어 있는 Schedulers로서 CPU 코어 수만큼의 스레드를 생성합니다.
 */

/**
 * - [Schedulers.fromExecutorService()]
 * 기존에 이미 사용하고 있는 ExecutorService가 있다면 이 ExecutorService로 부터 Scheduler를 생성하는 방식입니다.
 * ExecutorService로부터 직접 생성할 수도 있지만 Reactor에서는 이 방식을 권장하지는 않습니다.
 */

/**
 * - [Schedulers.newXXXX()]
 * Schedulers.single(), Schedulers.boundedElastic(), Schedulers.parallel()은 Reactor에서 제공하는 디폴트 Schedulers 인스턴스를 사용합니다.
 * 하지만 필요하다면 Schedulers.newSingle(), Schedulers.newBoundedElastic(), Schedulers.newParallel() 메서드를 사용해서 새로운 Scheduler 인스턴스를 생성할 수 있습니다.
 *
 * 즉, 스레드 이름, 생성 가능한 디폴트 스레드의 개수, 스레드의 유휴기간, 데몬 스레드로부터의 동작 여부 등을 직접 지정해서 커스텀 스레드 풀을 새로 생성할 수 있습니다.
 */
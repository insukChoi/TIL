## 정리
- 논리 트랙젠션을 하나라도 롤백되면 관련된 물리 트랜잭션은 롤백된다.
- 이 문제를 해결하려면 'REQUIRES_NEW'를 사용해서 트랜젝션을 분리해야 한다.
- 참고로 예제를 단순화 하기 위해 'MemberService'가 'MemberRepository', 'LogRepository'만 호출하지만 실제로는 더 많은
리포지토리들을 호출하고 그 중에 'LogRepository'만 트랜잭션을 분리한다고 생각한다.
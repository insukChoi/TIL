## Reactor 에서 제공하는 Backpressure 전략 종류
| 종류        | 설명                                                                                       |
|-----------|------------------------------------------------------------------------------------------|
| IGNORE 전략 | Backpressure 를 적용하지 않는다.                                                                 |
| ERROR 전략  | Downstream 으로 전달할 데이터가 버퍼에 가득 찰 경우, <br/>Exception 을 발생시키는 전략                            |
| DROP 전략   | Downstream 으로 전달할 데이터가 버퍼에 가득 찰 경우, <br/>버퍼 밖에서 대기하는 먼저 emit 된 데이터부터 Drop 시키는 전략         |
| LATEST 전략 | Downstream 으로 전달할 데이터가 버퍼에 가득 찰 경우, <br/>버퍼 밖에서 대기하는 가장 최근에(나중에) emit 된 데이터부터 버퍼에 채우는 전략 |
| BUFFER 전략 | Downstream 으로 전달할 데이터가 버퍼에 가득 찰 경우, <br/>버퍼 안에 있는 데이터부터 Drop 시키는 전략                      |
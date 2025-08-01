# [Gold III] 마법사 상어와 토네이도 - 20057 

[문제 링크](https://www.acmicpc.net/problem/20057) 

### 성능 요약

메모리: 35040 KB, 시간: 356 ms

### 분류

구현, 시뮬레이션

### 제출 일자

2025년 7월 31일 16:48:58

### 문제 설명

<p><a href="/problem/20056">마법사 상어</a>가 토네이도를 배웠고, 오늘은 토네이도를 크기가 N×N인 격자로 나누어진 모래밭에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 모래의 양을 의미한다.</p>

<p>토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다. 토네이도는 한 번에 한 칸 이동한다. 다음은 N = 7인 경우 토네이도의 이동이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/37e7aa13-0f2b-49d6-af68-e745537b1ea3/-/preview/" style="width: 250px; height: 253px;"></p>

<p>토네이도가 한 칸 이동할 때마다 모래는 다음과 같이 일정한 비율로 흩날리게 된다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/33b01ca0-4659-49f1-b126-8e042e17d3f1/-/preview/" style="width: 235px; height: 234px;"></p>

<p>토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다. 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다. α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다. 모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양은 더해진다. 위의 그림은 토네이도가 왼쪽으로 이동할 때이고, 다른 방향으로 이동하는 경우는 위의 그림을 해당 방향으로 회전하면 된다.</p>

<p>토네이도는 (1, 1)까지 이동한 뒤 소멸한다. 모래가 격자의 밖으로 이동할 수도 있다. 토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.</p>

### 입력 

 <p>첫째 줄에 격자의 크기 N이 주어진다. 둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.</p>

### 출력 

 <p>격자의 밖으로 나간 모래의 양을 출력한다.</p>


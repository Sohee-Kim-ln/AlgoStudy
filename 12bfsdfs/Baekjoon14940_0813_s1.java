package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

// 1시간 1분 5초
public class Baekjoon14940_0813_s1 {
	// 4방 델타변수
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		// n,m
		String tempS = bfr.readLine();
		StringTokenizer stz = new StringTokenizer(tempS, " ");

		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		// board, start
		int[][] board = new int[N][M];
		int[][] dist = new int[N][M];

		int rStart = 0, cStart = 0;

		for (int i = 0; i < N; i++) {
			tempS = bfr.readLine();
			stz = new StringTokenizer(tempS, " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(stz.nextToken());

				if (board[i][j] == 1)
					dist[i][j] = -1;

				if (board[i][j] == 2) {
					rStart = i;
					cStart = j;
				}
			}
		}

		// bfs
		Queue<int[]> quu = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		// 시작점
		quu.add(new int[] { rStart, cStart });
		visited[rStart][cStart] = true;

		// bfs 시작
		while (!quu.isEmpty()) {
			int[] now = quu.poll();
			int r = now[0];
			int c = now[1];

			// 4방 탐색
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				// 경계 확인
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				// 이동 가능여부 확인
				if (board[nr][nc] == 0) {
					dist[nr][nc] = 0;
					continue;
				}

				// 방문확인
				if (visited[nr][nc])
					continue;

				dist[nr][nc] = dist[r][c] + 1;
				visited[nr][nc] = true;
				quu.add(new int[] { nr, nc });
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(dist[i][j]);

				if (j != M - 1)
					sb.append(' ');
				else
					sb.append('\n');
			}
		}

		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}

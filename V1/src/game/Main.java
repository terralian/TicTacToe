package game;

/**
 * V1版本，没有界面，没有AI，但是有一个可完整运行的游戏核心逻辑
 */
public class Main {

    /**
     * 写死步骤来测试游戏是否正确运行
     */
    public static void main(String[] args) {
        // 平局
        TicTacToe game = new TicTacToe();
        game.mark(1, 1);
        game.mark(0, 1);
        game.mark(2, 0);
        game.mark(0, 2);
        game.mark(0, 0);
        game.mark(2, 2);
        game.mark(1, 2);
        game.mark(1, 0);
        GameStatus status = game.mark(2, 1);
        assertStatus(GameStatus.DRAW, status);

        // 圈（A）赢
        game.resetGame();
        game.mark(1, 1);
        game.mark(0, 2);
        game.mark(2, 1);
        game.mark(1, 2);
        status = game.mark(0, 1);
        assertStatus(GameStatus.A, status);

        // 叉（B）赢
        game.resetGame();
        game.mark(1, 1);
        game.mark(0, 2);
        game.mark(0, 0);
        game.mark(2, 2);
        game.mark(0, 1);
        game.mark(1, 2);
    }

    private static void assertStatus(GameStatus expectStatus, GameStatus actualStatus) {
        if (expectStatus != actualStatus) {
            throw new IllegalStateException("expect status: " + expectStatus + ", actual status：" + actualStatus);
        }
    }
}

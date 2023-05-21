package game;

/**
 * 井字棋游戏逻辑
 */
public class TicTacToe {

    private final Boolean[][] chessboard = new Boolean[3][3];
    private Boolean currentPlayer;
    private int usedChequerSize = 0;
    private GameStatus gameStatus = GameStatus.RUNNING;

    /**
     * 初始化创建一个3x3的棋盘规则的游戏
     */
    public TicTacToe() {
        this.currentPlayer = true;
    }

    /**
     * 在棋盘上画圈叉
     *
     * @param row 行[0, 3]
     * @param col 列[0, 3]
     * @return GameStatus
     */
    public GameStatus mark(int row, int col) {
        if (gameStatus != GameStatus.RUNNING) {
            throw new IllegalStateException("game is not running: " + gameStatus);
        }
        if (chessboard[row][col] != null) {
            throw new IllegalStateException("current cell has already set value：" + row + "," + col);
        }
        usedChequerSize++;
        chessboard[row][col] = currentPlayer;

        GameStatus gameStatus = calcGameStatus(row, col);
        this.gameStatus = gameStatus;
        // 交换玩家
        if (gameStatus == GameStatus.RUNNING) {
            setPlayerTurn(!currentPlayer);
        }
        return gameStatus;
    }

    /**
     * 重置游戏
     */
    public void resetGame() {
        this.currentPlayer = true;
        this.usedChequerSize = 0;
        this.gameStatus = GameStatus.RUNNING;
        // 清空棋盘
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length; j++) {
                chessboard[i][j] = null;
            }
        }
    }

    /**
     * 设置先手的玩家
     *
     * @param currentPlayer 当前玩家
     */
    public void setPlayerTurn(boolean currentPlayer) {
        this.currentPlayer = currentPlayer ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 计算游戏当前状态，进行中，平局，或某个玩家获胜
     */
    private GameStatus calcGameStatus(int row, int col) {
        if (isCurrentPlayerWin(row, col)) {
            return currentPlayer ? GameStatus.A : GameStatus.B;
        }
        if (usedChequerSize < 9) return GameStatus.RUNNING;
        return GameStatus.DRAW;
    }

    /**
     * 某个玩家是否在当次下子后获胜
     */
    private boolean isCurrentPlayerWin(int r, int c) {
        return (chessboard[r][0] == currentPlayer && chessboard[r][1] == currentPlayer && chessboard[r][2] == currentPlayer) //
                || (chessboard[0][c] == currentPlayer && chessboard[1][c] == currentPlayer && chessboard[2][c] == currentPlayer) //
                || (chessboard[0][0] == currentPlayer && chessboard[1][1] == currentPlayer && chessboard[2][2] == currentPlayer) //
                || (chessboard[2][0] == currentPlayer && chessboard[1][1] == currentPlayer && chessboard[0][2] == currentPlayer);
    }
}

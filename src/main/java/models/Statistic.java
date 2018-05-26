package models;

public class Statistic {

	private int rightAnswersCount;
	private int wrongAnswersCount;

	public Statistic(int rightAnswersCount, int wrongAnswersCount) {
		this.rightAnswersCount = rightAnswersCount;
		this.wrongAnswersCount = wrongAnswersCount;
	}

	public int getRightAnswersCount() {
		return rightAnswersCount;
	}

	public int getWrongAnswersCount() {
		return wrongAnswersCount;
	}
}

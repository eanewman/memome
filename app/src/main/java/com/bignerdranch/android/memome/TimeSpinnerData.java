package com.bignerdranch.android.memome;

public class TimeSpinnerData {
    protected final String spinnerText;
    protected final int spinnerTimeInt;

    public TimeSpinnerData(String spinnerText, int spinnerTimeInt) {
        this.spinnerText = spinnerText;
        this.spinnerTimeInt = spinnerTimeInt;
    }

    public int getSpinnerTimeInt() {
        return this.spinnerTimeInt;
    }

    public String getCompleteSpinnerString() {
        return this.spinnerText + "|" + this.spinnerTimeInt;
    }

    @Override
    public String toString() {
        return this.spinnerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSpinnerData that = (TimeSpinnerData) o;

        if (spinnerTimeInt != that.spinnerTimeInt) return false;
        return spinnerText != null ? spinnerText.equals(that.spinnerText) : that.spinnerText == null;

    }

    @Override
    public int hashCode() {
        int result = spinnerText != null ? spinnerText.hashCode() : 0;
        result = 31 * result + spinnerTimeInt;
        return result;
    }
}

package s4.specification;

public interface InformationEstimatorInterface {
    /**
     * Set the data for computing the information quantities.
     *
     * @param target the data
     */
    void setTarget(byte target[]);

    /**
     * Set the data for sample space to computer probability.
     *
     * @param space the data
     */
    void setSpace(byte space[]);

    /**
     * Estimate information quantity.
     *
     * It returns 0.0 when the TARGET is not set or TARGET's length is zero;
     * TARGETが設定されていないか、TARGETの長さがゼロの場合は0.0を返します
     * It returns Double.MAX_VALUE when the true value is infinite, or SPACE is not set.
     * 真の値が無限大またはSPACEが設定されていない場合はDouble.MAX_VALUEを返します
     * The behavior is undefined if the true value is finite but larger than Double.MAX_VALUE.
     * 真の値が有限でDouble.MAX_VALUEより大きい場合の動作は未定義です
     * Note that this happens only when the SPACE is unreasonably large.
     * これは、SPACEが不当に大きい場合にのみ発生することに注意してください
     * We will encounter other problem anyway.
     * とにかく他の問題に遭遇する
     * Otherwise, it returns the estimation of information quantity.
     * そうでなければ、情報量の推定を返す
     */
    double estimation();
}


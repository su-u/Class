class Random {
    constructor(m, s) {
        this.m = m;
        this.s = s;
    }

    get Random() {
        var a = 1 - Math.random();
        var b = 1 - Math.random();
        var c = Math.sqrt(-2 * Math.log(a));
        if (0.5 - Math.random() > 0) {
            return c * Math.sin(Math.PI * 2 * b) * this.s + this.m;
        } else {
            return c * Math.cos(Math.PI * 2 * b) * this.s + this.m;
        }
    }
}

/**
 * 正規分布乱数関数 参考:http://d.hatena.ne.jp/iroiro123/20111210/1323515616
 * @param number m 平均μ
 * @param number s 分散σ^2
 * @return number ランダムに生成された値
 */
var normRand = function (m, s) {
    var a = 1 - Math.random();
    var b = 1 - Math.random();
    var c = Math.sqrt(-2 * Math.log(a));
    if (0.5 - Math.random() > 0) {
        return c * Math.sin(Math.PI * 2 * b) * s + m;
    } else {
        return c * Math.cos(Math.PI * 2 * b) * s + m;
    }
};
export type RamenData = {
    name: string;
    desc: string;
    img: string;
    pd: string;
}

export const DATA: RamenData[] = [
    {
        name: '必勝軒(ひっしょうけん)',
        desc: '　津田沼駅から徒歩で約5分にある、1日寝かせて熟成させた自家製麺が人気のお店です。' +
            '基本の鶏・豚・魚介をバランスよく配合したスープに、ちょっと固めの自家製麺の歯応えが絶妙です。' +
            '軟らかく煮込まれたチャーシューもしっかりした味で、間違いないおいしさ。' +
            '盛りそばもバランススープ（月・火曜日）との相性がいい人気メニューです。' +
            '曜日によって味が違うスープは、水曜日の濃厚鶏豚スープがイチオシといわれています。' +
            'どの曜日のラーメンが好みか食べ比べてみるのも楽しいですね。\n',
        img: 'hissyou.jpg',
        pd: '!1m18!1m12!1m3!1d3240.574612058984!2d140.01743201567257!3d35.68747498019274!2m3!1f0!2f0!3f0!3m2!1i1024!2i76' +
            '8!4f13.1!3m3!1m2!1s0x60228027937189c3%3A0xf803259f28689070!2z5rSl55Sw5rK85b-F5Yud6LuS!5e0!3m2!1sja!2sjp!4v1' +
            '574850694569!5m2!1sja!2sjp'
    },
    {
        name: 'なりたけ',
        desc: '　こってりとした最上級の背脂がたくさんのったこってりスープが好評のラーメン店。' +
            'パリにも支店があり、Tシャツまで販売するユニークなお店です。' +
            '「醤油」と「味噌」はしっかり濃いめの味付けで、固めに茹でられたツルシコの中太麺との相性抜群。' +
            '背脂は、少な目・普通・多目・ギタギタから量が選べます。味を濃いと感じたらスープで割って薄めることも可能です。' +
            '豚のばら肉で作るチャーシューも口の中でとろけるほど柔らかく絶品です。濃い味ラーメンが食べたい時には絶対におすすめ！のラーメン店です。\n',
        img: 'naritake.jpg',
        pd: '!1m18!1m12!1m3!1d3240.328460386176!2d140.01899531567273!3d35.693533980191376!2m3!1f0!2f0!3f0!3m2!1i1024!2i7' +
            '68!4f13.1!3m3!1m2!1s0x60228020ff9dc671%3A0xc7e9861ca72bdc70!2z44Gq44KK44Gf44GRIOa0peeUsOayvOW6lw!5e0!3m2!1s' +
            'ja!2sjp!4v1574850729579!5m2!1sja!2sjp'
    },
    {
        name: '魚骨ラーメン 鈴木さん',
        desc: '　JR津田沼駅北口より徒歩で約5分にある、個性派でしっかりおいしいラーメンで知られるお店です。' +
            '秋刀魚の混ぜそばや梅昆布茶つけラーメン（夏季限定）など、他の店ではなかなか出会えないラーメンが勢揃い。' +
            '定番メニューの鈴木さんラーメンは、腰の強い細麺に魚骨醤油のやや黒っぽいスープ。魚の骨のダシが利いてほのかな苦みがあり、あっさり味にまとまっています。' +
            '店主の試行錯誤が垣間見られるラーメンを食べに訪れてみてはいかがでしょう。\n',
        img: 'suzuki.jpg',
        pd: '!1m18!1m12!1m3!1d3240.3008082083848!2d140.02098671567276!3d35.6942145801912!2m3!1f0!2f0!3f0!3m2!1i1024!2i76' +
            '8!4f13.1!3m3!1m2!1s0x6022802177c7f70b%3A0xbc8330d35eab2f0e!2z6a2a6aqo44KJ44O844KB44KT6Yi05pyo44GV44KT!5e0!3' +
            'm2!1sja!2sjp!4v1574850649847!5m2!1sja!2sjp'
    },
    {
        name: '九十九とんこつラーメン',
        desc: '　津田沼駅北口にある、「99%の九十九ラーメンにあなたの1%を加えて100%の味に」をコンセプトに連日行列必至のラーメンの名店です。' +
            '深いコクを感じる完成度の高いラーメンがいただけます。おすすめメニューは元祖○究チーズラーメンで、チーズ好きを中心に幅広い層に人気を集めています。' +
            'チーズは十勝産の生乳を100%使ったゴールデンゴーダチーズをふんだんに使い、マイルドな味に仕上がっています。' +
            '麺は中太麺でしっかりとコシがあり、食べごたえも見た目のインパクトも抜群です。\n',
        img: 'tsukumo.jpg',
        pd: '!1m18!1m12!1m3!1d3240.3509726428742!2d140.02049911567272!3d35.69297988019161!2m3!1f0!2f0!3f0!3m2!1i1024!2i7' +
            '68!4f13.1!3m3!1m2!1s0x60228021403a15b3%3A0x5c44ebbe44eeee2!2z5Lmd5Y2B5Lmd44Op44O844Oh44OzIOa0peeUsOayvOW6lw' +
            '!5e0!3m2!1sja!2sjp!4v1574850599414!5m2!1sja!2sjp'
    },
];

export const defaultData: RamenData = {
    name: '',
    desc: '',
    img: '',
    pd: ''
};

export const ramenByName = (name: string) => DATA.find(x => x.name === name);
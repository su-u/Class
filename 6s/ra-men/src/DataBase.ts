export type RamenData = {
    name: string;
    desc: string;
    img: string;
}

export const DATA: RamenData[] = [
    {
        name: 'ラーメン1',
        desc: 'aa',
        img: 'aaa'
    },
    {
        name: 'なりたけ',
        desc: '説明',
        img: ''
    }
];

export const defaultData: RamenData = {
    name: '',
    desc: '',
    img: ''
};

export const ramenByName = (name: string) => DATA.find(x => x.name === name);
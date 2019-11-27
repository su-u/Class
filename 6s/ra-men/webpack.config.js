const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    output: {
        path: `${__dirname}/dist`,
        filename: 'bundle.js?[hash]',
    },
    entry: path.resolve(__dirname, './src/index.tsx'),
    module: {
        rules: [
            {
                test: /\.tsx$/,
                use: ['thread-loader', 'cache-loader', 'babel-loader'],
                include: path.resolve('src'),
                exclude: /node_modules/,
            },
            {
                test: /\.html/,
                use:[
                    {loader: 'html-loader'}
                ],
            },
        ]
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.json'],
        plugins: [
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, './src/html/index.html')
        }),
    ],
    // externals: {
    //     "react": "React",
    //     "react-dom": "ReactDOM",
    // },
};
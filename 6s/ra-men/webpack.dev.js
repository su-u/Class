const merge = require('webpack-merge');
const common = require('./webpack.config.js');
const TsconfigPathsPlugin = require('tsconfig-paths-webpack-plugin');
const HardSourceWebpackPlugin = require('hard-source-webpack-plugin');
const webpack = require('webpack');

module.exports = merge(common, {
    mode: 'development',
    // output: {
    //     sourceMapFilename: 'bundle.js.map',
    // },
    devtool: 'inline-source-map',
    watchOptions: {
        poll: 500
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: {
                    loader: 'ts-loader',
                    options: {
                        configFile: "tsconfig.dev.json",
                        transpileOnly: true,
                        happyPackMode: true
                    }
                }
            },
        ]
    },
    resolve: {
        plugins: [
            new TsconfigPathsPlugin({ configFile: 'tsconfig.dev.json' }),
        ]
    },
    devServer: {
        port: 3000,
        contentBase: 'dist',
        watchContentBase: true,
        hot: true,
    },
    plugins: [
        new HardSourceWebpackPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ]
});
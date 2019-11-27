import React from 'react';
import { Switch, Route, HashRouter } from 'react-router-dom';
import App from '@/App';
import Top from '@/container/Top';

export default () => (
    <>
        <HashRouter>
            <Switch>
                <Route exact path={'/'} component={Top} />
                <Route path={'/:name'} component={App} />
            </Switch>
        </HashRouter>
    </>
);

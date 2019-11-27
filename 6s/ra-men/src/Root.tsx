import React from 'react';
import { Switch, Route, BrowserRouter } from 'react-router-dom';
import App from '@/App';
import Top from '@/Top';

export default () => (
    <>
        <BrowserRouter>
            <Switch>
                <Route exact path={'/'} component={Top} />
                <Route path={'/:name'} component={App} />
            </Switch>
        </BrowserRouter>
    </>
);

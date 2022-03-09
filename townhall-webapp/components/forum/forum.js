import classes from '../../styles/Home.module.css';

import { Fragment } from "react";

function Forum() {
  

    return (
       <Fragment>
           <h1>Forum</h1>
             <section>
                <div className={classes.topics}>
                        <div className={classes.topics_header}>
                                Topics
                        </div>
                        <div className={classes.posts_header}>
                                Posts
                        </div>
                        <div className={classes.views_header}>
                                Views
                        </div>
                        <div className={classes.lastpost_header}>
                                Last Post
                        </div>
                </div>
             </section>
       </Fragment>
    );
}

export default Forum; 
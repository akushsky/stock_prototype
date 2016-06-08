package com.akushsky.Parser

import org.springframework.stereotype.Service
/**
 * Created by akushsky on 6/8/16.
 */
@Service
interface DataFeedParser<T> {
    List<T> parse(InputStream inputStream);
}
import React from "react";
import "./_quoteGen.scss";
import { useState, useEffect } from "react";

export default function QuoteGen() {
    const [quote, setQuote] = useState("");

    useEffect(() => {
        let ignore = false;

        fetch("data/quotes.txt")
            .then((response) => response.text())
            .then((text) => {
                let quotes = text.split("\n");
                let randomNum = Math.floor(Math.random() * quotes.length);

                if (!ignore) setQuote(quotes.at(randomNum));
            });

        return () => {
            ignore = true;
        };
    }, [quote]);

    return (
        <aside className="quote-gen">
            <p className="quote">"{quote}"</p>
        </aside>
    );
}

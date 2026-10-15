import { addProp, capitalize, pipe, toCamelCase, toUpperCase } from "remeda";

type Section = {
    title: string;
    subtitle: string;
    link: string;
};

// Content things
const mainContents: Section[] = [
    {
        title: "Interests",
        subtitle: "Quirky arrangement of the things I like",
        link: "/interests",
    },
    {
        title: "Tension High",
        subtitle:
            "(Name might change) My endless character and video game writing project",
        link: "/tensionhigh",
    },
    {
        title: "Projects",
        subtitle: "Amazing things you should check out right now fr",
        link: "/projects",
    },
    {
        title: "Poems",
        subtitle: "Selection of the favorite poems I've written",
        link: "/selectedPoems",
    },
];

export type ContentType = Section & { next: Section, prev: Section }

export const Content = (() => {
    let object: Record<string, ContentType> = {};

    for (let index = 0; index < mainContents.length; index++) {
        const elem = mainContents[index]
        const prevElem = mainContents.at(index - 1)!!
        const nextElem = mainContents.at(index + 1) ?? mainContents[0]

        object = addProp(object,
            pipe(elem.title, toCamelCase(), capitalize()),
            pipe(elem, addProp('next', nextElem), addProp('prev', prevElem)))
    }

    return object;
})()
console.log(Content);


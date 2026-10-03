import { randomInteger } from "remeda";

/**
 * @return {number}
 * */
export function rotatingDec(value, length) {
    return value !== 0 ? value - 1 : length - 1;
}

/**
 * @return {number}
 * */
export function rotatingInc(value, length) {
    return value !== length - 1 ? value + 1 : 0;
}

/**
 * @param {any[]} array
 * @param {number} sampleSize
 */
export function takeRandom(array, sampleSize) {
    const usedIndexes = new Set()
    const resultArray = new Array()

    for (let index = 0; index < sampleSize; index++) {
        let index = randomInteger(0, array.length - 1)
        while (usedIndexes.has(index)) index = randomInteger(0, array.length - 1)
        usedIndexes.add(index);

        resultArray.push(array[index])
    }

    return resultArray
}

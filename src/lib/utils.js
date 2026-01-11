/**
 * @return {number}
 * */
export function rotatingDec(value, length)
{
    return value !== 0 ? value - 1 : length - 1;
}

/**
 * @return {number}
 * */
export function rotatingInc(value, length)
{
    return value !== length - 1 ? value + 1 : 0;
}

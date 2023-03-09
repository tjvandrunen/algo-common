int sum_bits_set(unsigned char vect) 
{
    int bits = 0;
    int i;
    for (i = 0; i < 8; i++)
    {
        bits += vect & 1;
        vect >>= 1;
    }
    return bits;
}

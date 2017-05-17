package yahooapi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public final class Decimal implements Comparable<Decimal>, Serializable {

	private static final long serialVersionUID = 2225130444465033658L;

	public static final MathContext MATH_CONTEXT = new MathContext(32, RoundingMode.HALF_UP);

    /** Not-a-Number instance (infinite error) */
    public static final Decimal NaN = new Decimal();

    public static final Decimal ZERO = valueOf(0);
    public static final Decimal ONE = valueOf(1);
    public static final Decimal TWO = valueOf(2);
    public static final Decimal THREE = valueOf(3);
    public static final Decimal TEN = valueOf(10);
    public static final Decimal HUNDRED = valueOf(100);
    public static final Decimal THOUSAND = valueOf(1000);

    private final BigDecimal delegate;

    
    private Decimal() {
        delegate = null;
    }

    private Decimal(String val) {
        delegate = new BigDecimal(val, MATH_CONTEXT);
    }

    private Decimal(double val) {
        delegate = new BigDecimal(val, MATH_CONTEXT);
    }

    private Decimal(int val) {
        delegate = new BigDecimal(val, MATH_CONTEXT);
    }

    private Decimal(long val) {
        delegate = new BigDecimal(val, MATH_CONTEXT);
    }

    private Decimal(BigDecimal val) {
        delegate = val;
    }


    public Decimal plus(Decimal augend) {
        if ((this == NaN) || (augend == NaN)) {
            return NaN;
        }
        return new Decimal(delegate.add(augend.delegate, MATH_CONTEXT));
    }


    public Decimal minus(Decimal subtrahend) {
        if ((this == NaN) || (subtrahend == NaN)) {
            return NaN;
        }
        return new Decimal(delegate.subtract(subtrahend.delegate, MATH_CONTEXT));
    }

    public Decimal multipliedBy(Decimal multiplicand) {
        if ((this == NaN) || (multiplicand == NaN)) {
            return NaN;
        }
        return new Decimal(delegate.multiply(multiplicand.delegate, MATH_CONTEXT));
    }

    public Decimal dividedBy(Decimal divisor) {
        if ((this == NaN) || (divisor == NaN) || divisor.isZero()) {
            return NaN;
        }
        return new Decimal(delegate.divide(divisor.delegate, MATH_CONTEXT));
    }


    public Decimal remainder(Decimal divisor) {
        if ((this == NaN) || (divisor == NaN) || divisor.isZero()) {
            return NaN;
        }
        return new Decimal(delegate.remainder(divisor.delegate, MATH_CONTEXT));
    }


    public Decimal pow(int n) {
        if (this == NaN) {
            return NaN;
        }
        return new Decimal(delegate.pow(n, MATH_CONTEXT));
    }

    public Decimal log() {
        if (this == NaN) {
            return NaN;
        }
        return new Decimal(StrictMath.log(delegate.doubleValue()));
    }


    public Decimal sqrt() {
        if (this == NaN) {
            return NaN;
        }
        return new Decimal(StrictMath.sqrt(delegate.doubleValue()));
    }


    public Decimal abs() {
        if (this == NaN) {
            return NaN;
        }
        return new Decimal(delegate.abs());
    }

    public boolean isZero() {
        if (this == NaN) {
            return false;
        }
        return compareTo(ZERO) == 0;
    }

 
    public boolean isPositive() {
        if (this == NaN) {
            return false;
        }
        return compareTo(ZERO) > 0;
    }

    public boolean isPositiveOrZero() {
        if (this == NaN) {
            return false;
        }
        return compareTo(ZERO) >= 0;
    }

    public boolean isNaN() {
        return this == NaN;
    }

    public boolean isNegative() {
        if (this == NaN) {
            return false;
        }
        return compareTo(ZERO) < 0;
    }

    public boolean isNegativeOrZero() {
        if (this == NaN) {
            return false;
        }
        return compareTo(ZERO) <= 0;
    }

    public boolean isEqual(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return false;
        }
        return compareTo(other) == 0;
    }

    public boolean isGreaterThan(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return false;
        }
        return compareTo(other) > 0;
    }

    public boolean isGreaterThanOrEqual(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return false;
        }
        return compareTo(other) > -1;
    }


    public boolean isLessThan(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return false;
        }
        return compareTo(other) < 0;
    }

    public boolean isLessThanOrEqual(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return false;
        }
        return compareTo(other) < 1;
    }

    @Override
    public int compareTo(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return 0;
        }
        return delegate.compareTo(other.delegate);
    }

    public Decimal min(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return NaN;
        }
        return (compareTo(other) <= 0 ? this : other);
    }

    public Decimal max(Decimal other) {
        if ((this == NaN) || (other == NaN)) {
            return NaN;
        }
        return (compareTo(other) >= 0 ? this : other);
    }

    public double toDouble() {
        if (this == NaN) {
            return Double.NaN;
        }
        return delegate.doubleValue();
    }

    @Override
    public String toString() {
        if (this == NaN) {
            return "NaN";
        }
        return delegate.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.delegate != null ? this.delegate.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Decimal)) {
            return false;
        }
        final Decimal other = (Decimal) obj;
        if (this.delegate != other.delegate
                && (this.delegate == null || (this.delegate.compareTo(other.delegate) != 0))) {
            return false;
        }
        return true;
    }

    public static Decimal valueOf(String val) {
        if ("NaN".equals(val)) {
            return NaN;
        }
        return new Decimal(val);
    }

    public static Decimal valueOf(double val) {
        if (Double.isNaN(val)) {
            return NaN;
        }
        return new Decimal(val);
    }

    public static Decimal valueOf(int val) {
        return new Decimal(val);
    }

    public static Decimal valueOf(long val) {
        return new Decimal(val);
    }
}

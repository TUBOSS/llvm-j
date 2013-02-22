package org.llvm;

import static org.llvm.binding.LLVMLibrary.LLVMAlignOf;
import static org.llvm.binding.LLVMLibrary.LLVMArrayType;
import static org.llvm.binding.LLVMLibrary.LLVMConstAllOnes;
import static org.llvm.binding.LLVMLibrary.LLVMConstInt;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfArbitraryPrecision;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfString;
import static org.llvm.binding.LLVMLibrary.LLVMConstIntOfStringAndSize;
import static org.llvm.binding.LLVMLibrary.LLVMConstNull;
import static org.llvm.binding.LLVMLibrary.LLVMConstPointerNull;
import static org.llvm.binding.LLVMLibrary.LLVMConstReal;
import static org.llvm.binding.LLVMLibrary.LLVMConstRealOfString;
import static org.llvm.binding.LLVMLibrary.LLVMConstRealOfStringAndSize;
import static org.llvm.binding.LLVMLibrary.LLVMCountParamTypes;
import static org.llvm.binding.LLVMLibrary.LLVMCountStructElementTypes;
import static org.llvm.binding.LLVMLibrary.LLVMDoubleType;
import static org.llvm.binding.LLVMLibrary.LLVMDoubleTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFP128Type;
import static org.llvm.binding.LLVMLibrary.LLVMFP128TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFloatType;
import static org.llvm.binding.LLVMLibrary.LLVMFloatTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMFunctionType;
import static org.llvm.binding.LLVMLibrary.LLVMGetArrayLength;
import static org.llvm.binding.LLVMLibrary.LLVMGetElementType;
import static org.llvm.binding.LLVMLibrary.LLVMGetIntTypeWidth;
import static org.llvm.binding.LLVMLibrary.LLVMGetParamTypes;
import static org.llvm.binding.LLVMLibrary.LLVMGetPointerAddressSpace;
import static org.llvm.binding.LLVMLibrary.LLVMGetReturnType;
import static org.llvm.binding.LLVMLibrary.LLVMGetStructElementTypes;
import static org.llvm.binding.LLVMLibrary.LLVMGetTypeContext;
import static org.llvm.binding.LLVMLibrary.LLVMGetTypeKind;
import static org.llvm.binding.LLVMLibrary.LLVMGetUndef;
import static org.llvm.binding.LLVMLibrary.LLVMGetVectorSize;
import static org.llvm.binding.LLVMLibrary.LLVMInt16Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt16TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt1Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt1TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt32Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt32TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt64Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt64TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMInt8Type;
import static org.llvm.binding.LLVMLibrary.LLVMInt8TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMIntType;
import static org.llvm.binding.LLVMLibrary.LLVMIntTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMIsFunctionVarArg;
import static org.llvm.binding.LLVMLibrary.LLVMIsPackedStruct;
import static org.llvm.binding.LLVMLibrary.LLVMLabelType;
import static org.llvm.binding.LLVMLibrary.LLVMLabelTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMPPCFP128Type;
import static org.llvm.binding.LLVMLibrary.LLVMPPCFP128TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMPointerType;
import static org.llvm.binding.LLVMLibrary.LLVMSizeOf;
import static org.llvm.binding.LLVMLibrary.LLVMStructType;
import static org.llvm.binding.LLVMLibrary.LLVMStructTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMVectorType;
import static org.llvm.binding.LLVMLibrary.LLVMVoidType;
import static org.llvm.binding.LLVMLibrary.LLVMVoidTypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMX86FP80Type;
import static org.llvm.binding.LLVMLibrary.LLVMX86FP80TypeInContext;
import static org.llvm.binding.LLVMLibrary.LLVMX86MMXType;
import static org.llvm.binding.LLVMLibrary.LLVMX86MMXTypeInContext;

import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.llvm.binding.LLVMLibrary.LLVMTypeKind;
import org.llvm.binding.LLVMLibrary.LLVMTypeRef;

/**
 * Each value in the LLVM IR has a type, an LLVMTypeRef.
 */
public class TypeRef {

    private LLVMTypeRef type;

    public LLVMTypeRef type() {
        return type;
    }

    TypeRef(LLVMTypeRef type) {
        this.type = type;
    }

    /**
     * Obtain the enumerated type of a Type instance.<br>
     * 
     * @see llvm::Type:getTypeID()
     */
    public ValuedEnum<LLVMTypeKind> getTypeKind() {
        return LLVMGetTypeKind(type);
    }

    /**
     * Obtain the context to which this type instance is associated.<br>
     * 
     * @see llvm::Type::getContext()
     */
    public Context getTypeContext() {
        return new Context(LLVMGetTypeContext(type));
    }

    /**
     * Obtain an integer type from a context with specified bit width.
     */
    public static TypeRef int1TypeInContext(Context C) {
        return new TypeRef(LLVMInt1TypeInContext(C.context()));
    }

    public static TypeRef int8TypeInContext(Context C) {
        return new TypeRef(LLVMInt8TypeInContext(C.context()));
    }

    public static TypeRef int16TypeInContext(Context C) {
        return new TypeRef(LLVMInt16TypeInContext(C.context()));
    }

    public static TypeRef int32TypeInContext(Context C) {
        return new TypeRef(LLVMInt32TypeInContext(C.context()));
    }

    public static TypeRef int64TypeInContext(Context C) {
        return new TypeRef(LLVMInt64TypeInContext(C.context()));
    }

    public static TypeRef intTypeInContext(Context C, int NumBits) {
        return new TypeRef(LLVMIntTypeInContext(C.context(), NumBits));
    }

    /**
     * Obtain an integer type from the global context with a specified bit<br>
     * width.
     */
    public static TypeRef int1Type() {
        return new TypeRef(LLVMInt1Type());
    }

    public static TypeRef int8Type() {
        return new TypeRef(LLVMInt8Type());
    }

    public static TypeRef int16Type() {
        return new TypeRef(LLVMInt16Type());
    }

    public static TypeRef int32Type() {
        return new TypeRef(LLVMInt32Type());
    }

    public static TypeRef int64Type() {
        return new TypeRef(LLVMInt64Type());
    }

    public static TypeRef intType(int NumBits) {
        return new TypeRef(LLVMIntType(NumBits));
    }

    public int getIntTypeWidth() {
        return LLVMGetIntTypeWidth(type);
    }

    /**
     * Obtain a 32-bit floating point type from a context.
     */
    public static TypeRef floatTypeInContext(Context C) {
        return new TypeRef(LLVMFloatTypeInContext(C.context()));
    }

    /**
     * Obtain a 64-bit floating point type from a context.
     */
    public static TypeRef doubleTypeInContext(Context C) {
        return new TypeRef(LLVMDoubleTypeInContext(C.context()));
    }

    /**
     * Obtain a 80-bit floating point type (X87) from a context.
     */
    public static TypeRef x86FP80TypeInContext(Context C) {
        return new TypeRef(LLVMX86FP80TypeInContext(C.context()));
    }

    /**
     * Obtain a 128-bit floating point type (112-bit mantissa) from a<br>
     * context.
     */
    public static TypeRef FP128TypeInContext(Context C) {
        return new TypeRef(LLVMFP128TypeInContext(C.context()));
    }

    /**
     * Obtain a 128-bit floating point type (two 64-bits) from a context.
     */
    public static TypeRef PPCFP128TypeInContext(Context C) {
        return new TypeRef(LLVMPPCFP128TypeInContext(C.context()));
    }

    public static TypeRef floatType() {
        return new TypeRef(LLVMFloatType());
    }

    public static TypeRef doubleType() {
        return new TypeRef(LLVMDoubleType());
    }

    public static TypeRef x86FP80Type() {
        return new TypeRef(LLVMX86FP80Type());
    }

    public static TypeRef FP128Type() {
        return new TypeRef(LLVMFP128Type());
    }

    public static TypeRef PPCFP128Type() {
        return new TypeRef(LLVMPPCFP128Type());
    }

    /**
     * Obtain a function type consisting of a specified signature.<br>
     * The function is defined as a tuple of a return Type, a list of<br>
     * parameter types, and whether the function is variadic.
     */
    public static TypeRef functionType(LLVMTypeRef ReturnType,
            Pointer<LLVMTypeRef> ParamTypes, int ParamCount, int IsVarArg) {
        return new TypeRef(LLVMFunctionType(ReturnType, ParamTypes, ParamCount,
                IsVarArg));
    }

    public static TypeRef functionType(TypeRef ReturnType,
            TypeRef... ParamTypes) {
        int N = ParamTypes.length;
        LLVMTypeRef[] inner = new LLVMTypeRef[N];
        for (int i = 0; i < N; i++) {
            inner[i] = ParamTypes[i].type;
        }

        Pointer<LLVMTypeRef> array = Pointer.allocateTypedPointers(
                LLVMTypeRef.class, ParamTypes.length);
        array.setArray(inner);

        return new TypeRef(LLVMFunctionType(ReturnType.type, array,
                ParamTypes.length, 0));
    }

    /**
     * Returns whether a function type is variadic.
     */
    public boolean isFunctionVarArg() {
        return LLVMIsFunctionVarArg(type) != 0;
    }

    /**
     * Obtain the Type this function Type returns.
     */
    public TypeRef getReturnType() {
        return new TypeRef(LLVMGetReturnType(type));
    }

    /**
     * Obtain the number of parameters this function accepts.
     */
    public int countParamTypes() {
        return LLVMCountParamTypes(type);
    }

    /**
     * Obtain the types of a function's parameters.<br>
     * The Dest parameter should point to a pre-allocated array of<br>
     * LLVMTypeRef at least LLVMCountParamTypes() large. On return, the<br>
     * first LLVMCountParamTypes() entries in the array will be populated<br>
     * with LLVMTypeRef instances.<br>
     * 
     * @param Dest
     *            Memory address of an array to be filled with result.
     */
    public void getParamTypes(Pointer<LLVMTypeRef> Dest) {
        LLVMGetParamTypes(type, Dest);
    }

    /**
     * Create a new structure type in a context.<br>
     * A structure is specified by a list of inner elements/types and<br>
     * whether these can be packed together.<br>
     * 
     * @see llvm::StructType::create()
     */
    public static TypeRef structTypeInContext(Context C,
            Pointer<LLVMTypeRef> ElementTypes, int ElementCount, boolean Packed) {
        return new TypeRef(LLVMStructTypeInContext(C.context(), ElementTypes,
                ElementCount, Packed ? 1 : 0));
    }

    /**
     * Create a new structure type in the global context.<br>
     * 
     * @see llvm::StructType::create()
     */
    public static TypeRef structType(Pointer<LLVMTypeRef> ElementTypes,
            int ElementCount, boolean Packed) {
        return new TypeRef(LLVMStructType(ElementTypes, ElementCount,
                Packed ? 1 : 0));
    }

    /**
     * Get the number of elements defined inside the structure.<br>
     * 
     * @see llvm::StructType::getNumElements()
     */
    public int countStructElementTypes() {
        return LLVMCountStructElementTypes(type);
    }

    /**
     * Get the elements within a structure.<br>
     * The function is passed the address of a pre-allocated array of<br>
     * LLVMTypeRef at least LLVMCountStructElementTypes() long. After<br>
     * invocation, this array will be populated with the structure's<br>
     * elements. The objects in the destination array will have a lifetime<br>
     * of the structure type itself, which is the lifetime of the context it<br>
     * is contained in.
     */
    public void getStructElementTypes(Pointer<LLVMTypeRef> Dest) {
        LLVMGetStructElementTypes(type, Dest);
    }

    /**
     * Determine whether a structure is packed.<br>
     * 
     * @see llvm::StructType::isPacked()
     */
    public boolean isPackedStruct() {
        return LLVMIsPackedStruct(type) != 0;
    }

    /**
     * Create a fixed size array type that refers to a specific type.<br>
     * The created type will exist in the context that its element type<br>
     * exists in.<br>
     * 
     * @see llvm::ArrayType::get()
     */
    public TypeRef arrayType(int ElementCount) {
        return new TypeRef(LLVMArrayType(type, ElementCount));
    }

    /**
     * Create a pointer type that points to a defined type.<br>
     * The created type will exist in the context that its pointee type<br>
     * exists in.<br>
     * 
     * @see llvm::PointerType::get()
     */
    public TypeRef pointerType(int AddressSpace) {
        return new TypeRef(LLVMPointerType(type, AddressSpace));
    }

    /**
     * Create a vector type that contains a defined type and has a specific<br>
     * number of elements.<br>
     * The created type will exist in the context thats its element type<br>
     * exists in.<br>
     * 
     * @see llvm::VectorType::get()
     */
    public TypeRef vectorType(int ElementCount) {
        return new TypeRef(LLVMVectorType(type, ElementCount));
    }

    /**
     * Obtain the type of elements within a sequential type.<br>
     * This works on array, vector, and pointer types.<br>
     * 
     * @see llvm::SequentialType::getElementType()
     */
    public TypeRef getElementType() {
        return new TypeRef(LLVMGetElementType(type));
    }

    /**
     * Obtain the length of an array type.<br>
     * This only works on types that represent arrays.<br>
     * 
     * @see llvm::ArrayType::getNumElements()
     */
    public int getArrayLength() {
        return LLVMGetArrayLength(type);
    }

    /**
     * Obtain the address space of a pointer type.<br>
     * This only works on types that represent pointers.<br>
     * 
     * @see llvm::PointerType::getAddressSpace()
     */
    public int getPointerAddressSpace() {
        return LLVMGetPointerAddressSpace(type);
    }

    /**
     * Obtain the number of elements in a vector type.<br>
     * This only works on types that represent vectors.<br>
     * 
     * @see llvm::VectorType::getNumElements()
     */
    public int getVectorSize() {
        return LLVMGetVectorSize(type);
    }

    /**
     * Create a void type in a context.
     */
    public static TypeRef voidTypeInContext(Context C) {
        return new TypeRef(LLVMVoidTypeInContext(C.context()));
    }

    /**
     * Create a label type in a context.
     */
    public static TypeRef labelTypeInContext(Context C) {
        return new TypeRef(LLVMLabelTypeInContext(C.context()));
    }

    /*public static TypeRef opaqueTypeInContext(Context C) {
        return new TypeRef(LLVMOpaqueTypeInContext(C.context()));
    }*/

    /**
     * Create a X86 MMX type in a context.
     */
    public static TypeRef x86MMXTypeInContext(Context C) {
        return new TypeRef(LLVMX86MMXTypeInContext(C.context()));
    }

    /**
     * These are similar to the above functions except they operate on the<br>
     * global context.
     */
    public static TypeRef voidType() {
        return new TypeRef(LLVMVoidType());
    }

    public static TypeRef labelType() {
        return new TypeRef(LLVMLabelType());
    }

    /*public static TypeRef opaqueType() {
        return new TypeRef(LLVMOpaqueType());
    }*/

    public static TypeRef x86MMXType() {
        return new TypeRef(LLVMX86MMXType());
    }

    //public static TypeHandleRef createTypeHandle(LLVMTypeRef PotentiallyAbstractTy);
    //public static void    refineType(LLVMTypeRef AbstractTy, LLVMTypeRef ConcreteTy);
    //public static TypeRef resolveTypeHandle(LLVMTypeHandleRef TypeHandle);
    //public static void    disposeTypeHandle(LLVMTypeHandleRef TypeHandle);

    /**
     * Obtain a constant value referring to the null instance of a type.<br>
     * 
     * @see llvm::Constant::getNullValue()
     */
    public Value constNull() {
        return new Value(LLVMConstNull(type));
    }

    /**
     * Obtain a constant that is a constant pointer pointing to NULL for a<br>
     * specified type.
     */
    public Value constPointerNull() {
        return new Value(LLVMConstPointerNull(type));
    }

    /**
     * Obtain a constant value referring to the instance of a type<br>
     * consisting of all ones.<br>
     * This is only valid for integer types.<br>
     * * @see llvm::Constant::getAllOnesValue()
     */
    public Value constAllOnes() {
        return new Value(LLVMConstAllOnes(type));
    }

    /**
     * Obtain a constant value referring to an undefined value of a type.<br>
     * 
     * @see llvm::UndefValue::get()
     */
    public Value GetUndef() {
        return new Value(LLVMGetUndef(type));
    }

    /**
     * Obtain a constant value for an integer type.<br>
     * The returned value corresponds to a llvm::ConstantInt.<br>
     * 
     * @see llvm::ConstantInt::get()<br>
     * @param N
     *            The value the returned instance should refer to.<br>
     * @param SignExtend
     *            Whether to sign extend the produced value.
     */
    public Value constInt(long N, boolean SignExtend) {
        return new Value(LLVMConstInt(type, N, SignExtend ? 1 : 0));
    }

    /**
     * Obtain a constant value for an integer of arbitrary precision.<br>
     * 
     * @see llvm::ConstantInt::get()
     */
    // TODO: change Pointer to array
    public Value constIntOfArbitraryPrecision(int NumWords, Pointer<Long> Words) {
        return new Value(
                LLVMConstIntOfArbitraryPrecision(type, NumWords, Words));
    }

    /**
     * Obtain a constant value for an integer parsed from a string.<br>
     * A similar API, LLVMConstIntOfStringAndSize is also available. If the<br>
     * string's length is available, it is preferred to call that function<br>
     * instead.<br>
     * 
     * @see llvm::ConstantInt::get()
     */
    public Value constIntOfString(String Text, byte Radix) {
        return new Value(LLVMConstIntOfString(type,
                Pointer.pointerToCString(Text), Radix));
    }

    /**
     * Obtain a constant value for an integer parsed from a string with<br>
     * specified length.<br>
     * 
     * @see llvm::ConstantInt::get()
     */
    public Value constIntOfStringAndSize(String Text, int SLen, byte Radix) {
        return new Value(LLVMConstIntOfStringAndSize(type,
                Pointer.pointerToCString(Text), SLen, Radix));
    }

    /**
     * Obtain a constant value referring to a double floating point value.
     */
    public Value constReal(double N) {
        return new Value(LLVMConstReal(type, N));
    }

    /**
     * Obtain a constant for a floating point value parsed from a string.<br>
     * A similar API, LLVMConstRealOfStringAndSize is also available. It<br>
     * should be used if the input string's length is known.
     */
    public Value constRealOfString(String Text) {
        return new Value(LLVMConstRealOfString(type,
                Pointer.pointerToCString(Text)));
    }

    /**
     * Obtain a constant for a floating point value parsed from a string.
     */
    public Value constRealOfStringAndSize(String Text, int SLen) {
        return new Value(LLVMConstRealOfStringAndSize(type,
                Pointer.pointerToCString(Text), SLen));
    }

    public Value alignOf(TypeRef Ty) {
        return new Value(LLVMAlignOf(type));
    }

    public Value sizeOf(TypeRef Ty) {
        return new Value(LLVMSizeOf(type));
    }

}
